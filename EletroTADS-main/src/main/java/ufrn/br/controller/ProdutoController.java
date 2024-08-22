package ufrn.br.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ufrn.br.dto.ProdutoRequestDTO;
import ufrn.br.dto.ProdutoResponseDTO;
import ufrn.br.model.*;
import ufrn.br.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos/")
@AllArgsConstructor
public class ProdutoController {
    private final ProdutoService service;
    private final ModelMapper mapper;

    @GetMapping
    public Page<ProdutoResponseDTO> listAll(Pageable pageable) {
        Page<Produto> produtosPage = service.listAll(pageable);
        return produtosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoRequestDTO productDto){
        Produto created = service.create(convertToEntity(productDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable("id") Long id){
        Produto produto = service.findById(id);
        ProdutoResponseDTO produtoDto = mapper.map(produto, ProdutoResponseDTO.class);

        return ResponseEntity.ok(produtoDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable("id") Long id, @RequestBody ProdutoRequestDTO productDto){
        try {
            Produto produto = service.findById(id);
        } catch (Exception e) {
            return this.create(productDto);
        }

        Produto updated = service.update(mapper.map(productDto, Produto.class), id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    private ProdutoResponseDTO convertToDto(Produto product){
        ProdutoResponseDTO produtoDto = mapper.map(product, ProdutoResponseDTO.class);
        produtoDto.addLinks(product);

        return produtoDto;
    }

    private Produto convertToEntity(ProdutoRequestDTO productDto){
        Produto produto = mapper.map(productDto, Produto.class);
        Set<Categoria> categorias = productDto.getCategorias().stream()
                .map(dto -> mapper.map(dto, Categoria.class))
                .collect(Collectors.toSet());

        produto.setCategorias(categorias);

        return produto;
    }
}