package ufrn.br.springrestweb2024.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public abstract class GenericService<T, ID, REPO extends JpaRepository<T, ID>> implements IService<T, ID>{

    private final REPO repository;

    public GenericService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> listAll() {
        return repository.findAll();
    }

    @Override
    public T listById(ID id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Objeto de id " + id + " not found");
    }

    @Override
    public void deleteById(ID id) {

        T entity = this.listById(id);
        this.repository.delete(entity);
    }

    @Override
    public T update(T entity, ID id) {
        //Adicionar lógica de negócio para comparação dos objetos
        //enviados e identificação de algum erro de update
        return this.repository.saveAndFlush(entity);
    }
}







