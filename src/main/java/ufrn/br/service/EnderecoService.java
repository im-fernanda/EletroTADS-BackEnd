package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.model.Endereco;
import ufrn.br.repository.EnderecoRepository;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class EnderecoService extends GenericCrudService<Endereco, Long, EnderecoRepository> {
    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }
}
