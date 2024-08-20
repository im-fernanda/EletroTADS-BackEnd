package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.EnderecoController;
import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.controller.UsuarioController;
import ufrn.br.model.Endereco;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.model.Usuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class PerfilUsuarioResponseDTO extends RepresentationModel<PerfilUsuarioResponseDTO> {
    String nome;
    String genero;
    String dataNascimento;

    public void addLinks(PerfilUsuario perfilUser){
        this.add(linkTo(PerfilUsuarioController.class).slash(perfilUser.getId()).withSelfRel());
       // this.add(linkTo(UsuarioController.class).slash(perfilUser.getId()).withRel("usuario"));

    }
}
