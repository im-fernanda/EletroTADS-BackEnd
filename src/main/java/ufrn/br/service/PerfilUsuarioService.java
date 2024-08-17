package ufrn.br.service;

import org.springframework.stereotype.Service;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.repository.PerfilUsuarioRepository;


@Service
public class PerfilUsuarioService extends GenericService<PerfilUsuario, Long, PerfilUsuarioRepository> {

    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        super(repository);
    }


}
