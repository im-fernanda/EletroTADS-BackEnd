package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.domain.Endereco;
import ufrn.br.repository.EnderecoRepository;

@Service
public class EnderecoService extends GenericService<Endereco, Long, EnderecoRepository>{
    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }
}
