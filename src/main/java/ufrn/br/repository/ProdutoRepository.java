package ufrn.br.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ufrn.br.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p LEFT JOIN FETCH p.categorias")
    Page<Produto> findAllWithCategorias(Pageable pageable);
}