package com.example.demo.dto;

import com.example.demo.controller.CategoriaController;
import com.example.demo.controller.ProdutoController;
import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto extends RepresentationModel<ProdutoResponseDto> {
    String nome;
    String descricao;
    float preco;
    int quantidade;
    List<CategoriaResponseDto> categorias;

    public void addLinks(Produto produto) {
        this.add(linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());

        if(produto.getCategorias() != null){
            for(Categoria categoria : produto.getCategorias()){
                this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withRel("categorias"));
            }
        }
    }
}
