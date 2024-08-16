package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.domain.Usuario;
import ufrn.br.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioService extends GenericService<Usuario, UUID, UsuarioRepository> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

}
