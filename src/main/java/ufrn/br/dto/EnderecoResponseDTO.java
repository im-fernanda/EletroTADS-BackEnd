package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.EnderecoController;
import ufrn.br.controller.PerfilUsuarioController;
import ufrn.br.model.Endereco;
import ufrn.br.model.PerfilUsuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;

    public void addLinks(Endereco endereco) {
        this.add(linkTo(EnderecoController.class).slash(endereco.getId()).withSelfRel());
    }
}
