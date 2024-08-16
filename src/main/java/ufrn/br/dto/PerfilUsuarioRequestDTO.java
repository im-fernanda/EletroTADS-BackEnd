package ufrn.br.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilUsuarioRequestDTO {
    private String nome;
    private String genero;
    private String dataNascimento;


}
