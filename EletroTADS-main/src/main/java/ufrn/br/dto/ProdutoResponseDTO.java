package ufrn.br.dto;

import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoController;
import ufrn.br.domain.Categoria;
import ufrn.br.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO> {
    String nome;
    String descricao;
    float preco;
    int quantidade;
    List<CategoriaResponseDTO> categorias;

    public void addLinks(Produto produto) {
        this.add(linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());

        if(produto.getCategorias() != null){
            for(Categoria categoria : produto.getCategorias()){
                this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withRel("categorias"));
            }
        }
    }
}
