package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.model.Categoria;
import ufrn.br.repository.CategoriaRepository;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class CategoriaService extends GenericCrudService<Categoria, Long, CategoriaRepository> {
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }
}