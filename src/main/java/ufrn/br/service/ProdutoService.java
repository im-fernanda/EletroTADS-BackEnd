package com.example.demo.service;

import com.example.demo.domain.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericService<Produto, Long, ProdutoRepository>{
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
