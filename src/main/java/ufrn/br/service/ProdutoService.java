package ufrn.br.service;


import org.springframework.stereotype.Service;
import ufrn.br.domain.Produto;
import ufrn.br.repository.ProdutoRepository;


@Service
public class ProdutoService extends GenericService<Produto, Long, ProdutoRepository> {
    public ProdutoService(ProdutoRepository repository) {
        super(repository);
    }
}
