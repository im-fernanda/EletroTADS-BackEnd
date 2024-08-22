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
public class CategoriaResponseDto extends RepresentationModel<CategoriaResponseDto> {
    String nome;
    // List<ProdutoResponseDto> produtos;

    public void addLinks(Categoria categoria){
        this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withSelfRel());

        if(categoria.getProdutos() != null){
            for(Produto produto : categoria.getProdutos()){
                this.add(linkTo(ProdutoController.class).slash(produto.getId()).withRel("produtos"));
            }
        }
    }
}
