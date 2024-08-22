package ufrn.br.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufrn.br.dto.CategoriaRequestDTO;
import ufrn.br.dto.CategoriaResponseDTO;
import ufrn.br.dto.ProdutoRequestDTO;
import ufrn.br.dto.ProdutoResponseDTO;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import ufrn.br.repository.CategoriaRepository;
import ufrn.br.service.CategoriaService;
import ufrn.br.service.ProdutoService;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto-categorias/")
@AllArgsConstructor
public class ProdutoCategoriaController {
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final ModelMapper mapper;
    private final CategoriaRepository categoriaRepository;

    @PostMapping("categorias/")
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@RequestBody CategoriaRequestDTO categoriaDto) {
        Categoria created = categoriaService.create(convertToEntityCategoria(categoriaDto));


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDtoCategoria(created));
    }

    @PostMapping("produtos/")
    public ResponseEntity<ProdutoResponseDTO> createProduto(@RequestBody ProdutoRequestDTO produtoDto) {
        Produto created = produtoService.create(convertToEntityProduto(produtoDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDtoProduto(created));
    }

    @GetMapping("categorias/")
    public Page<CategoriaResponseDTO> listAllCategorias(Pageable pageable) {
        Page<Categoria> categoriasPage = categoriaService.listAll(pageable);
        return categoriasPage.map(this::convertToDtoCategoria);
    }

    @GetMapping("produtos/")
    public Page<ProdutoResponseDTO> listAllProdutos(Pageable pageable) {
        Page<Produto> produtosPage = produtoService.listAll(pageable);
        return produtosPage.map(this::convertToDtoProduto);
    }

    @PatchMapping
    public Categoria produtosUpdate(Categoria categoria, Produto produto) {
        return categoriaService.patchUpdate(categoria, produto);
    }

    private CategoriaResponseDTO convertToDtoCategoria(Categoria categoria) {
        CategoriaResponseDTO categoriaDto = mapper.map(categoria, CategoriaResponseDTO.class);
        categoriaDto.addLinks(categoria);

        return categoriaDto;
    }

    private Categoria convertToEntityCategoria(CategoriaRequestDTO categoriaDto) {
        Categoria categoria = mapper.map(categoriaDto, Categoria.class);
        Set<Produto> produtos = categoriaDto.getProdutos().stream()
                .map(dto -> mapper.map(dto, Produto.class))
                .collect(Collectors.toSet());

        categoria.setProdutos(produtos);

        return categoria;
    }

    private ProdutoResponseDTO convertToDtoProduto(Produto produto) {
        ProdutoResponseDTO produtoDto = mapper.map(produto, ProdutoResponseDTO.class);
        produtoDto.addLinks(produto);

        return produtoDto;
    }

    private Produto convertToEntityProduto(ProdutoRequestDTO produtoDto) {
        Produto produto = mapper.map(produtoDto, Produto.class);
        Set<Categoria> categorias = produtoDto.getCategoriasIds().stream()
                .map(dto -> {
                    Categoria categoria = categoriaService.findById(dto);
                    Categoria updatedCategoria = produtosUpdate(categoria, produto);
                    return updatedCategoria;
                })
                .collect(Collectors.toSet());

        produto.setCategorias(categorias);

        return produto;
    }
}
