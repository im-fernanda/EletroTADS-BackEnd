package ufrn.br.dto;

import ufrn.br.model.Usuario;
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
    Boolean isAdmin;
    PerfilUsuarioRequestDTO perfilUsuario;
    List<EnderecoRequestDTO> enderecos;
}
