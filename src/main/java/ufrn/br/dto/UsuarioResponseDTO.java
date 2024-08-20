package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.EnderecoController;
import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.controller.UsuarioController;
import ufrn.br.model.Endereco;
import ufrn.br.model.Usuario;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {
    Long id;
    String username;
    Boolean isAdmin;
    //PerfilUsuarioResponseDTO perfilUsuario;
    //List<EnderecoResponseDTO> enderecos;

    public void addLinks(Usuario user){
        this.add(linkTo(UsuarioController.class).slash(user.getId()).withSelfRel());
    //    this.add(linkTo(PerfilUsuarioController.class).slash(user.getPerfilUsuario().getId()).withRel("perfil"));

    //    if(user.getEnderecos() != null){
    //        for(Endereco endereco : user.getEnderecos()){
     //           this.add(linkTo(EnderecoController.class).slash(endereco.getId()).withRel("enderecos"));
    //        }
    //    }
    }
}