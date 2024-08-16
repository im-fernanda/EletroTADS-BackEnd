package ufrn.br.dto;

import lombok.Data;

@Data
public class ProdutoRequestDTO {

    private String nome;
    private String descricao;
    private int preco;
    private int estoque;
}