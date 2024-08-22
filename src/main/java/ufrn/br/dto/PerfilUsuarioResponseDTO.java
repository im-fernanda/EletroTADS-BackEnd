package com.example.demo.dto;

import com.example.demo.controller.CategoriaController;
import com.example.demo.controller.PerfilUsuarioController;
import com.example.demo.controller.ProdutoController;
import com.example.demo.domain.Categoria;
import com.example.demo.domain.PerfilUsuario;
import com.example.demo.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioResponseDto extends RepresentationModel<PerfilUsuarioResponseDto> {
    String nome;
    String genero;
    String dataNascimento;

    public void addLinks(PerfilUsuario perfil){
        this.add(linkTo(PerfilUsuarioController.class).slash(perfil.getId()).withSelfRel());
    }
}
