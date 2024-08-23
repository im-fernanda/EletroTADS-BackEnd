package ufrn.br.service;

import ufrn.br.model.Endereco;
import ufrn.br.repository.EnderecoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends GenericCrudService<Endereco, Long, EnderecoRepository> {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
        enderecoRepository = repository;
    }

    public Page<Endereco> listAllEnderecos(Pageable pageable) {
        return enderecoRepository.listAllByUser(pageable);
    }
}
