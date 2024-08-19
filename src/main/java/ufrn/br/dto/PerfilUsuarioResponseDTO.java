package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioResponseDTO {

    private Long id;
    private String nome;
    private String genero;
    private String dataNascimento;

}
