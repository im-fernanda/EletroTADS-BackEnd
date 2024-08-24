package ufrn.br.controller;

import ufrn.br.domain.Categoria;
import ufrn.br.service.CategoriaService;
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

import java.net.URI;

@RestController
@RequestMapping("/categorias/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody CategoriaRequestDTO categoriaDto) {
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
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaDto) {
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
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id) {
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(convertToDto(categoria));
    }

    @GetMapping
    public Page<CategoriaResponseDTO> listAll(Pageable pageable) {
        Page<Categoria> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private CategoriaResponseDTO convertToDto(Categoria categoria){
        return mapper.map(categoria, CategoriaResponseDTO.class);
    }

    private Categoria convertToEntity(@RequestBody CategoriaRequestDTO categoriaDto){
        return mapper.map(categoriaDto, Categoria.class);
    }
}
