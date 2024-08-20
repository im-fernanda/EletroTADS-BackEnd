package ufrn.br.dto;

import lombok.Data;

@Data
public class PerfilUsuarioResponseDTO {

    private Long id;
    private String nome;
    private String genero;
    private String dataNascimento;

}
