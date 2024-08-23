package ufrn.br.dto;

import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoController;
import ufrn.br.domain.Categoria;
import ufrn.br.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO extends RepresentationModel<CategoriaResponseDTO> {
    String nome;
    // List<ProdutoResponseDto> produtos;

    public void addLinks(Categoria categoria){
        this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withSelfRel());

        if(categoria.getProdutos() != null){
            for(Produto produto : categoria.getProdutos()){
                this.add(linkTo(ProdutoController.class).slash(produto.getId()).withRel("produtos"));
            }
        }
    }
}
