package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.domain.Endereco;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDto extends RepresentationModel<PessoaResponseDto> {
    String nome;
    int idade;
    String sexo;
    Endereco endereco;
}
