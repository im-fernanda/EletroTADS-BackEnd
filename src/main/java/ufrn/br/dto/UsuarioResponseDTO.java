package com.example.demo.dto;

import com.example.demo.controller.EnderecoController;
import com.example.demo.controller.PerfilUsuarioController;
import com.example.demo.controller.UsuarioController;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto extends RepresentationModel<UsuarioResponseDto> {
    String username;
    Boolean isAdmin;
    PerfilUsuarioResponseDto perfilUsuario;
    List<EnderecoResponseDto> enderecos;

    public void addLinks(Usuario user){
        this.add(linkTo(UsuarioController.class).slash(user.getId()).withSelfRel());
        this.add(linkTo(PerfilUsuarioController.class).slash(user.getPerfilUsuario().getId()).withRel("perfil"));

        if(user.getEnderecos() != null){
            for(Endereco endereco : user.getEnderecos()){
                this.add(linkTo(EnderecoController.class).slash(endereco.getId()).withRel("enderecos"));
            }
        }
    }
}
