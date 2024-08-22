package com.example.demo.service;

import com.example.demo.domain.PerfilUsuario;
import com.example.demo.repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService extends GenericService<PerfilUsuario, Long, PerfilUsuarioRepository> {
    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        super(repository);
    }
}
