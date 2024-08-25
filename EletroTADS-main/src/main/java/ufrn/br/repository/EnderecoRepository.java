package ufrn.br.repository;

import org.springframework.data.repository.query.Param;
import ufrn.br.domain.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.usuario.id = :id_usuario")
    Page<Endereco> listAllByUser(@Param("id_usuario") Long id_usuario, Pageable pageable);
}
