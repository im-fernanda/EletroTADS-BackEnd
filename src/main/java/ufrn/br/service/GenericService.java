package ufrn.br.service;

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
    public Optional<T> listById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T update(T entity, ID id) {
        return repository.save(entity);
    }
}
