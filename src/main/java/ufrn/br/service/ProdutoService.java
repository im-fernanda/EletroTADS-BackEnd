package ufrn.br.service;

import ufrn.br.domain.Produto;
import ufrn.br.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class ProdutoService extends GenericCrudService<Produto, Long, ProdutoRepository> {
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
