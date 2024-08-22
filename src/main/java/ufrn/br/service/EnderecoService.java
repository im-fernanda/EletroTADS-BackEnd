package com.example.demo.service;

import com.example.demo.domain.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends GenericService<Endereco, Long, EnderecoRepository> {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
        enderecoRepository = repository;
    }

    public Page<Endereco> listAllEnderecos(Pageable pageable) {
        return enderecoRepository.listAllByUser(pageable);
    }
}
