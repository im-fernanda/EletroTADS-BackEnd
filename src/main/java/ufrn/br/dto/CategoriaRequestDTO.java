package ufrn.br.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoriaRequestDTO {

    String nome;
    List<ProdutoRequestDTO> produtos;
}

