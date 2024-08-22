package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDto {
    String nome;
    String descricao;
    float preco;
    int estoque;
    List<Long> ids_categorias;
}
