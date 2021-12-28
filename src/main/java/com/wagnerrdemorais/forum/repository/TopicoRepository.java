package com.wagnerrdemorais.forum.repository;

import com.wagnerrdemorais.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable pageable);
}
