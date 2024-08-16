package ufrn.br.dto;

import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private int preco;
    private int estoque;
}