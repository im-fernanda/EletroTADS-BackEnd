package ufrn.br.dto;

import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.domain.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioResponseDTO extends RepresentationModel<PerfilUsuarioResponseDTO> {
    String nome;
    String genero;
    String dataNascimento;

    public void addLinks(PerfilUsuario perfil){
        this.add(linkTo(PerfilUsuarioController.class).slash(perfil.getId()).withSelfRel());
    }
}
