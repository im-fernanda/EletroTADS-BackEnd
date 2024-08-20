package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioResponseDTO extends RepresentationModel<PerfilUsuarioResponseDTO> {

    private Long id;
    private String nome;
    private String genero;
    private String dataNascimento;

}
