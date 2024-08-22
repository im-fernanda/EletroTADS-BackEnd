package ufrn.br.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<T, ID> {
    public T create(T entity);
    public Page<T> listAll(Pageable pageable);
    public T findById(ID id);
    public void deleteById(ID id);
    public T update(T entity, ID id);
}