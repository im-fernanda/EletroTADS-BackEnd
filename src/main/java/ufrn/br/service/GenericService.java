package com.example.demo.service;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T, ID, REPO extends JpaRepository<T, ID>> implements IService<T, ID> {

    private REPO repository;

    public GenericService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Page<T> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Override
    public T findById(ID id) {
        Optional<T> entityOptional = repository.findById(id);
        if(entityOptional.isPresent()) {
            return entityOptional.get();
        }

        throw new EntityNotFoundException("Entity not found");
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T update(T entity, ID id) {
        Optional<T> entityOptional = repository.findById(id);
        if(entityOptional.isPresent()) {
            return repository.saveAndFlush(entity);
        }

        throw new EntityNotFoundException("Entity not found");
    }
}
