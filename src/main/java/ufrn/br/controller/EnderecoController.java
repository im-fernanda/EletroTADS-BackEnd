package com.example.demo.controller;

import com.example.demo.domain.Endereco;
import com.example.demo.domain.Usuario;
import com.example.demo.dto.EnderecoRequestDto;
import com.example.demo.dto.EnderecoResponseDto;
import com.example.demo.service.EnderecoService;
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

@RestController
@RequestMapping("/enderecos/")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService service;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> create(@RequestBody EnderecoRequestDto enderecoDto) {
        Usuario usuario = usuarioService.findById(enderecoDto.getId_usuario());
        Endereco endereco = convertToEntity(enderecoDto);
        endereco.setUsuario(usuario);

        Endereco created = service.create(endereco);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> update(@PathVariable Long id, @RequestBody EnderecoRequestDto enderecoDto) {
        try{
            Endereco endereco = service.findById(id);
        } catch (Exception e) {
            return this.create(enderecoDto);
        }

        Endereco endereco = convertToEntity(enderecoDto);
        endereco.setId(id);
        Endereco updated = service.update(endereco, endereco.getId());

        return ResponseEntity.ok(convertToDto(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoResponseDto> findById(@PathVariable Long id) {
        Endereco endereco = service.findById(id);
        return ResponseEntity.ok(convertToDto(endereco));
    }

    @GetMapping
    public Page<EnderecoResponseDto> listAll(Pageable pageable) {
        Page<Endereco> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private EnderecoResponseDto convertToDto(Endereco endereco){
        return mapper.map(endereco, EnderecoResponseDto.class);
    }

    private Endereco convertToEntity(@RequestBody EnderecoRequestDto enderecoDto){
        return mapper.map(enderecoDto, Endereco.class);
    }
}
