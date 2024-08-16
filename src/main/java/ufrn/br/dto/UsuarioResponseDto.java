package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.domain.Endereco;
import ufrn.br.domain.PerfilUsuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto extends RepresentationModel<UsuarioResponseDto> {
    private UUID id;
    private String username;
    private boolean isAdmin;
    private List<Endereco> enderecos;
    private PerfilUsuario perfilUsuario;

    //Add links

}
