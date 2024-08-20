package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.controller.CategoriaController;
import ufrn.br.controller.ProdutoController;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO extends RepresentationModel<ProdutoResponseDTO> {
    String nome;
    String descricao;
    float preco;
    int quantidade;
    List<CategoriaResponseDTO> categorias;

    public void addLinks(Produto produto) {
        this.add(linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());

        if(produto.getCategoria() != null){
            for(Categoria categoria : produto.getCategoria()){
                this.add(linkTo(CategoriaController.class).slash(categoria.getId()).withRel("categoria"));
            }
        }
    }
}