package ufrn.br.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO extends RepresentationModel<ProdutoResponseDTO> {
    String nome;
    String descricao;
    float preco;
    int estoque;
    Set<CategoriaResponseDTO> categorias;

}