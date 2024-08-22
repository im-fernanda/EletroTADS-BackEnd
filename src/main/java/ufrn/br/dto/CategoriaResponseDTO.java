package ufrn.br.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoController;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import java.util.Set;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class CategoriaResponseDTO extends RepresentationModel<CategoriaResponseDTO> {
    String nome;
    Set<ProdutoResponseDTO> produtos;

    public void addLinks(Categoria categoria){
        this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withSelfRel());

        if(categoria.getProdutos() != null){
            for(Produto produto : categoria.getProdutos()){
                this.add(linkTo(ProdutoController.class).slash(produto.getId()).withRel("produto"));
            }
        }
    }
}