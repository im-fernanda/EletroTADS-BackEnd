package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.model.PerfilUsuario;
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
