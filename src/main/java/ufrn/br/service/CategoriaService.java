package ufrn.br.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import ufrn.br.domain.Categoria;
import ufrn.br.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class CategoriaService extends GenericCrudService<Categoria, Long, CategoriaRepository> {

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Categoria findByNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Categoria not found"));
    }

}
