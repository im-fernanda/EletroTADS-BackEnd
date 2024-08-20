package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;
}
