package ufrn.br.service;

import ufrn.br.model.Categoria;
import ufrn.br.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends GenericCrudService<Categoria, Long, CategoriaRepository> {
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }
}
