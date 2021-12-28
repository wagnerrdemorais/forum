package com.wagnerrdemorais.forum.controller.form;

import com.wagnerrdemorais.forum.modelo.Topico;
import com.wagnerrdemorais.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String titulo;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (!topico.isPresent()) {
            return null;
        } else {
            Topico top = topico.get();
            top.setTitulo(this.titulo);
            top.setMensagem(this.mensagem);
            return topicoRepository.save(top);
        }
    }
}
