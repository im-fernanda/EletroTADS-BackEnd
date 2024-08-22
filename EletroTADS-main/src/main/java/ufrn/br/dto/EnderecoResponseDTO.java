package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.EnderecoController;
import ufrn.br.model.Endereco;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;

    public void addLinks(Endereco endereco) {
        this.add(linkTo(EnderecoController.class).slash(endereco.getId()).withSelfRel());
    }
}
