package ufrn.br.dto;

import lombok.Data;
import java.util.List;

@Data
public class UsuarioRequestDTO{

    String username;
    String senha;
    Boolean isAdmin;
    PerfilUsuarioRequestDTO perfilUsuario;
    List<EnderecoRequestDTO> enderecos;
}
