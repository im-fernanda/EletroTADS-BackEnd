package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO> {

    private Long id;
    private String nome;
    private String descricao;
    private int preco;
    private int estoque;
}