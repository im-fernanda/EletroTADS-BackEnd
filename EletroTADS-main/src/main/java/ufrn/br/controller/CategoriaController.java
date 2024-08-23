package ufrn.br.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufrn.br.dto.CategoriaRequestDTO;
import ufrn.br.dto.CategoriaResponseDTO;
import ufrn.br.model.Categoria;
import ufrn.br.model.Produto;
import ufrn.br.service.CategoriaService;
import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias/")
@AllArgsConstructor
public class CategoriaController {
    private final CategoriaService service;
    private final ModelMapper mapper;

    @GetMapping
    public Page<CategoriaResponseDTO> listAll(Pageable pageable) {
        Page<Categoria> categoriasPage = service.listAll(pageable);
        return categoriasPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody CategoriaRequestDTO categoryDto){
        Categoria created = service.create(convertToEntity(categoryDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable("id") Long id){
        Categoria categoria = service.findById(id);
        CategoriaResponseDTO categoriaDto = mapper.map(categoria, CategoriaResponseDTO.class);

        return ResponseEntity.ok(categoriaDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable("id") Long id, @RequestBody CategoriaRequestDTO categoryDto){
        try {
            Categoria categoria = service.findById(id);
        } catch (Exception e) {
            return this.create(categoryDto);
        }

        Categoria updated = service.update(mapper.map(categoryDto, Categoria.class), id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    private CategoriaResponseDTO convertToDto(Categoria category){
        CategoriaResponseDTO categoriaDto = mapper.map(category, CategoriaResponseDTO.class);
        categoriaDto.addLinks(category);

        return categoriaDto;
    }

    private Categoria convertToEntity(CategoriaRequestDTO categoryDto){
        Categoria categoria = mapper.map(categoryDto, Categoria.class);
        Set<Produto> produtos = categoryDto.getProdutos().stream()
                .map(dto -> mapper.map(dto, Produto.class))
                .collect(Collectors.toSet());

        categoria.setProdutos(produtos);

        return categoria;
    }
}

