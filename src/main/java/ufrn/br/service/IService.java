package ufrn.br.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    public T create(T entity);
    public List<T> listAll();
    public T findById(ID id);
    public void deleteById(ID id);
    public T update(T entity, ID id);
}