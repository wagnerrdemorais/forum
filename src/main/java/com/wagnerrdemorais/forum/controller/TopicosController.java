package com.wagnerrdemorais.forum.controller;

import com.wagnerrdemorais.forum.controller.dto.TopicoDto;
import com.wagnerrdemorais.forum.controller.form.TopicoForm;
import com.wagnerrdemorais.forum.modelo.Topico;
import com.wagnerrdemorais.forum.repository.CursoRepository;
import com.wagnerrdemorais.forum.repository.TopicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public List<TopicoDto> lista(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
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
}
