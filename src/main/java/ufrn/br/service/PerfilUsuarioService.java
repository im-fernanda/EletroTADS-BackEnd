package ufrn.br.service;

import ufrn.br.domain.PerfilUsuario;
import ufrn.br.repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class PerfilUsuarioService extends GenericCrudService<PerfilUsuario, Long, PerfilUsuarioRepository> {
    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        super(repository);
    }
}
