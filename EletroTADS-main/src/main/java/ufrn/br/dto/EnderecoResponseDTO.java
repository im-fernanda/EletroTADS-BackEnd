package ufrn.br.dto;

import ufrn.br.controller.EnderecoController;
import ufrn.br.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
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
