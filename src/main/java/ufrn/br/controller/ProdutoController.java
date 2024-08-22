package com.example.demo.controller;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Produto;
import com.example.demo.domain.Usuario;
import com.example.demo.dto.EnderecoRequestDto;
import com.example.demo.dto.EnderecoResponseDto;
import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EnderecoService;
import com.example.demo.service.ProdutoService;
import com.example.demo.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<ProdutoResponseDto> create(@RequestBody ProdutoRequestDto produtoDto) {
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
    public ResponseEntity<ProdutoResponseDto> update(@PathVariable Long id, @RequestBody ProdutoRequestDto produtoDto) {
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
    public ResponseEntity<ProdutoResponseDto> findById(@PathVariable Long id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok(convertToDto(produto));
    }

    @GetMapping
    public Page<ProdutoResponseDto> listAll(Pageable pageable) {
        Page<Produto> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private ProdutoResponseDto convertToDto(Produto produto){
        return mapper.map(produto, ProdutoResponseDto.class);
    }

    private Produto convertToEntity(@RequestBody ProdutoRequestDto produtoDto){
        return mapper.map(produtoDto, Produto.class);
    }
}
