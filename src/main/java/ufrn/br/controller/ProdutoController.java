package ufrn.br.controller;

import ufrn.br.dto.ProdutoRequestDTO;
import ufrn.br.dto.ProdutoResponseDTO;
import ufrn.br.model.Categoria;
import ufrn.br.model.Endereco;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.model.Produto;
import ufrn.br.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/produtos/")
@AllArgsConstructor
public class ProdutoController {
    private final ProdutoService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<ProdutoResponseDTO> listAll(){
        return service.listAll().stream().map(this::convertToDto).collect(toList());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoRequestDTO productDto){
        Produto created = service.create(convertToEntity(productDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
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
        ProdutoResponseDTO usuarioDto = mapper.map(product, ProdutoResponseDTO.class);
        usuarioDto.addLinks(product);

        return usuarioDto;
    }


    private Produto convertToEntity(ProdutoRequestDTO productDto){
        Produto produto = mapper.map(productDto, Produto.class);
        List<Categoria> categorias = productDto.getCategoria().stream()
                .map(dto -> mapper.map(dto, Categoria.class))
                .collect(Collectors.toList());

        produto.setCategoria(categorias);

        return produto;
    }
}