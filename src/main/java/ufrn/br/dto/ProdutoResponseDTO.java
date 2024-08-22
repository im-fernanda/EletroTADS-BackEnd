package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoCategoriaController;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import java.util.Set;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO> {
    Long id;
    String nome;
    String descricao;
    float preco;
    int estoque;
    Set<CategoriaResponseDTO> categorias;

    public void addLinks(Produto product){
        this.add(linkTo(ProdutoCategoriaController.class).slash(product.getId()).withSelfRel());

        if(product.getCategorias() != null){
            for(Categoria categoria : product.getCategorias()){
                this.add(linkTo(ProdutoCategoriaController.class).slash(categoria.getId()).withRel("categorias"));
            }
        }
    }
}