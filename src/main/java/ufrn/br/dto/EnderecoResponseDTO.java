package com.example.demo.dto;

import com.example.demo.controller.EnderecoController;
import com.example.demo.controller.UsuarioController;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDto extends RepresentationModel<EnderecoResponseDto> {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;

    public void addLinks(Endereco endereco){
        this.add(linkTo(EnderecoController.class).slash(endereco.getId()).withSelfRel());
    }
}
