package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoController;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import java.util.Set;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO> {

    private Long id;
    private String nome;
    private String descricao;
    private int preco;
    private int estoque;
    private Set<CategoriaResponseDTO> categorias;

    public void addLinks(Produto product){
        this.add(linkTo(ProdutoController.class).slash(product.getId()).withSelfRel());

        if(product.getCategorias() != null){
            for(Categoria categoria : product.getCategorias()){
                this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withRel("categorias"));
            }
        }
    }
}