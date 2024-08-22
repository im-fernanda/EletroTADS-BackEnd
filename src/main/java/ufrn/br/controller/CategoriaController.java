package com.example.demo.controller;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.dto.CategoriaRequestDto;
import com.example.demo.dto.CategoriaResponseDto;
import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProdutoService;
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
@RequestMapping("/categorias/")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@RequestBody CategoriaRequestDto categoriaDto) {
        Categoria categoria = convertToEntity(categoriaDto);
        Categoria created = service.create(categoria);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> update(@PathVariable Long id, @RequestBody CategoriaRequestDto categoriaDto) {
        try{
            Categoria categoria = service.findById(id);
        } catch (Exception e) {
            return this.create(categoriaDto);
        }

        Categoria categoria = convertToEntity(categoriaDto);
        categoria.setId(id);

        Categoria updated = service.update(categoria, categoria.getId());

        return ResponseEntity.ok(convertToDto(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDto> findById(@PathVariable Long id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(convertToDto(categoria));
    }

    @GetMapping
    public Page<CategoriaResponseDto> listAll(Pageable pageable) {
        Page<Categoria> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private CategoriaResponseDto convertToDto(Categoria categoria){
        return mapper.map(categoria, CategoriaResponseDto.class);
    }

    private Categoria convertToEntity(@RequestBody CategoriaRequestDto categoriaDto){
        return mapper.map(categoriaDto, Categoria.class);
    }
}
