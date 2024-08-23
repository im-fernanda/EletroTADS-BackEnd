package ufrn.br.service;

import ufrn.br.model.Produto;
import ufrn.br.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericCrudService<Produto, Long, ProdutoRepository>{
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
