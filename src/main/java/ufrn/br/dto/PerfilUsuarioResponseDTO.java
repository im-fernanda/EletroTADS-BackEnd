package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.Link;
import ufrn.br.controller.EnderecoController;
import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.controller.UsuarioController;
import ufrn.br.model.Endereco;
import ufrn.br.model.PerfilUsuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class PerfilUsuarioResponseDTO {
    String nome;
    String genero;
    String dataNascimento;

    public void addLinks(PerfilUsuario perfilUsuario) {
        this.add(linkTo(PerfilUsuarioController.class).slash(perfilUsuario.getId()).withSelfRel());
    }

}
