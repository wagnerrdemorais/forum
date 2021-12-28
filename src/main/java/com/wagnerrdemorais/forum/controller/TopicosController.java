package com.wagnerrdemorais.forum.controller;

import com.wagnerrdemorais.forum.controller.dto.TopicoDetalhesDto;
import com.wagnerrdemorais.forum.controller.dto.TopicoDto;
import com.wagnerrdemorais.forum.controller.form.AtualizacaoTopicoForm;
import com.wagnerrdemorais.forum.controller.form.TopicoForm;
import com.wagnerrdemorais.forum.modelo.Topico;
import com.wagnerrdemorais.forum.repository.CursoRepository;
import com.wagnerrdemorais.forum.repository.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    TopicosController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
                                 @RequestParam() int pagina,
                                 @RequestParam() int qtd) {

        Pageable pageable = PageRequest.of(pagina, qtd);

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(pageable);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, pageable);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@Valid @RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {

        Topico topico = topicoForm.toEntity(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        TopicoDto topicoDto = new TopicoDto(topico);
        return ResponseEntity.created(uri).body(topicoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhesDto> detalhar(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        return topicoOptional
                .map(topico -> ResponseEntity.ok(new TopicoDetalhesDto(topico)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

        Optional<Topico> topicoOpt = topicoRepository.findById(id);

        if (!topicoOpt.isPresent()){
           return ResponseEntity.notFound().build();
        } else {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

    }
}
