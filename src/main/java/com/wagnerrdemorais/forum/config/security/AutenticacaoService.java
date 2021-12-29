package com.wagnerrdemorais.forum.config.security;

import com.wagnerrdemorais.forum.modelo.Usuario;
import com.wagnerrdemorais.forum.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = repository.findByEmail(username);
        return usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
    }

}
