package ufrn.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
