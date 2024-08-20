package ufrn.br.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ufrn.br.dto.CategoriaResponseDTO;
import ufrn.br.dto.EnderecoResponseDTO;
import ufrn.br.model.Categoria;
import ufrn.br.model.Endereco;
import ufrn.br.service.EnderecoService;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/enderecos/")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService service;
    private final ModelMapper mapper;

    @GetMapping
    public Page<EnderecoResponseDTO> listAll(Pageable pageable) {
        Page<Endereco> enderecosPage = service.listAll(pageable);
        return enderecosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> create(@RequestBody EnderecoResponseDTO enderecoDTO) {
        Endereco created = service.create(convertToEntity(enderecoDTO));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoResponseDTO> getById(@PathVariable("id") Long id){
        Endereco endereco = service.findById(id);
        EnderecoResponseDTO enderecoDto = mapper.map(endereco, EnderecoResponseDTO.class);

        return ResponseEntity.ok(enderecoDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable("id") Long id, @RequestBody EnderecoResponseDTO enderecoDTO) {
        Endereco endereco;
        try {
            endereco = service.findById(id);
        } catch (Exception e) {
            return this.create(enderecoDTO);
        }

        Endereco updated = service.update(mapper.map(enderecoDTO, Endereco.class), id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    private EnderecoResponseDTO convertToDto(Endereco endereco) {
        EnderecoResponseDTO enderecoDto = mapper.map(endereco, EnderecoResponseDTO.class);
        enderecoDto.addLinks(endereco);
        return enderecoDto;
    }

    private Endereco convertToEntity(EnderecoResponseDTO enderecoDTO) {
        return mapper.map(enderecoDTO, Endereco.class);
    }
}
