package ufrn.br.dto;

import lombok.Data;

@Data
public class PerfilUsuarioRequestDTO {
    String nome;
    String genero;
    String dataNascimento;
}