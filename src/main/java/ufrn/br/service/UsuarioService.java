package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.model.Usuario;
import ufrn.br.repository.UsuarioRepository;
import ufrn.br.service.generic.GenericCrudService;

import java.util.UUID;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

}
