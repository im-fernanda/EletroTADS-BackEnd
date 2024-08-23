package ufrn.br.controller;

import ufrn.br.dto.ProdutoRequestDTO;
import ufrn.br.dto.ProdutoResponseDTO;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufrn.br.service.ProdutoService;
import ufrn.br.service.CategoriaService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos/")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService service;
    private final CategoriaService usuarioService;
    private final ModelMapper mapper;
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoRequestDTO produtoDto) {
        Produto produto = convertToEntity(produtoDto);

        List<Categoria> categorias = produtoDto.getIds_categorias().stream()
                .map(categoriaService::findById)
                .collect(Collectors.toList());

        produto.setCategorias(categorias);
        Produto created = service.create(produto);

        categorias.forEach(categoria -> categoria.getProdutos().add(created));
        categorias.forEach(categoriaService::create);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoDto) {
        try{
            Produto produto = service.findById(id);
        } catch (Exception e) {
            return this.create(produtoDto);
        }

        Produto produto = convertToEntity(produtoDto);
        produto.setId(id);

        List<Categoria> categorias = produtoDto.getIds_categorias().stream()
                .map(categoriaService::findById)
                .collect(Collectors.toList());

        produto.setCategorias(categorias);
        Produto updated = service.update(produto, produto.getId());

        categorias.forEach(categoria -> categoria.getProdutos().add(updated));
        categorias.forEach(categoriaService::create);

        return ResponseEntity.ok(convertToDto(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok(convertToDto(produto));
    }

    @GetMapping
    public Page<ProdutoResponseDTO> listAll(Pageable pageable) {
        Page<Produto> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private ProdutoResponseDTO convertToDto(Produto produto){
        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    private Produto convertToEntity(@RequestBody ProdutoRequestDTO produtoDto){
        return mapper.map(produtoDto, Produto.class);
    }
}
