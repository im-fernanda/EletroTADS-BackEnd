package com.example.demo.service;

import com.example.demo.domain.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends GenericService<Categoria, Long, CategoriaRepository> {
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }
}
