package ufrn.br.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
