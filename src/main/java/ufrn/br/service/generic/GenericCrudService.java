package ufrn.br.service.generic;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.service.interfaces.IService;

import java.util.Optional;

public abstract class GenericCrudService<T, ID, REPO extends JpaRepository<T, ID>> implements IService<T, ID> {

    protected REPO repository;

    public GenericCrudService(REPO repository) {
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
