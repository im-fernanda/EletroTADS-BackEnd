package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {
    String nome;
    String descricao;
    float preco;
    int estoque;
    List<String> nomes_categorias;
}
