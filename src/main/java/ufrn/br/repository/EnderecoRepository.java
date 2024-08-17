package ufrn.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
