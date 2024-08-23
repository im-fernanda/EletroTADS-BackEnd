package ufrn.br.service;

import ufrn.br.model.Usuario;
import ufrn.br.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
