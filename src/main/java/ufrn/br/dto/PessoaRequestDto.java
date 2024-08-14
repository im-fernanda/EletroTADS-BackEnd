package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.br.domain.Endereco;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDto {
    String nome;
    String dataNascimento;
    String sexo;
    Endereco endereco;
}
