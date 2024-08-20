package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.repository.PerfilUsuarioRepository;
import ufrn.br.service.generic.GenericCrudService;

@Service
public class PerfilUsuarioService extends GenericCrudService<PerfilUsuario, Long, PerfilUsuarioRepository> {
    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        super(repository);
    }
}