package ufrn.br.service;


import org.springframework.stereotype.Service;
import ufrn.br.repository.PessoaRepository;
import ufrn.br.domain.Pessoa;


@Service
public class PessoaService extends GenericService<Pessoa, Long, PessoaRepository> {
    public PessoaService(PessoaRepository repository) {
        super(repository);
    }
}
