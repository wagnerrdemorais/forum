package com.wagnerrdemorais.forum.controller.form;

import com.wagnerrdemorais.forum.modelo.Curso;
import com.wagnerrdemorais.forum.modelo.Topico;
import com.wagnerrdemorais.forum.repository.CursoRepository;
import com.wagnerrdemorais.forum.repository.TopicoRepository;

public class TopicoForm {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico toEntity(CursoRepository repository) {
        Curso curso = repository.findByNome(this.nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
