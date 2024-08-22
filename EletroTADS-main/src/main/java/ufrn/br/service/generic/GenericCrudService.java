package ufrn.br.service.generic;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.service.interfaces.ICrudService;
import java.util.Optional;

public abstract class GenericCrudService<T, ID, REPO extends JpaRepository<T, ID>> implements ICrudService<T, ID> {
    private final REPO repository;

    public GenericCrudService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Page<T> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T findById(ID id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Objeto de id " + id + " not found");
    }

    @Override
    public void deleteById(ID id) {
        T entity = this.findById(id);
        this.repository.delete(entity);
    }

    @Override
    public T update(T entity, ID id) {
        //Adicionar lógica de negócio para comparação dos objetos
        //enviados e identificação de algum erro de update
        return this.repository.saveAndFlush(entity);
    }
}