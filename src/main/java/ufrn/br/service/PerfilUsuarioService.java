package ufrn.br.service;

import ufrn.br.model.PerfilUsuario;
import ufrn.br.repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioService extends GenericCrudService<PerfilUsuario, Long, PerfilUsuarioRepository> {
    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        super(repository);
    }
}
