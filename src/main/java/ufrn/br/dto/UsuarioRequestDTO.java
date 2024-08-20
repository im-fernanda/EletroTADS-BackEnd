package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO{
    String username;
    String senha;
    Boolean isAdmin = false;
    //PerfilUsuarioRequestDTO perfilUsuario;
    //List<EnderecoRequestDTO> enderecos;
}
