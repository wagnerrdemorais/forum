package com.wagnerrdemorais.forum.controller;

import com.wagnerrdemorais.forum.controller.dto.TopicoDto;
import com.wagnerrdemorais.forum.modelo.Topico;
import com.wagnerrdemorais.forum.repository.TopicoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    private final TopicoRepository topicoRepository;

    TopicosController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @RequestMapping("/topicos")
    public List<TopicoDto> lista() {

        List<Topico> topicos = topicoRepository.findAll();

        return TopicoDto.converter(topicos);
    }
}
