package com.example.demo.controller;

import com.example.demo.domain.PerfilUsuario;
import com.example.demo.domain.Usuario;
import com.example.demo.dto.PerfilUsuarioRequestDto;
import com.example.demo.dto.PerfilUsuarioResponseDto;
import com.example.demo.service.PerfilUsuarioService;
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
@RequestMapping("/perfil/")
@AllArgsConstructor
public class PerfilUsuarioController {

    private final PerfilUsuarioService service;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<PerfilUsuarioResponseDto> create(@RequestBody PerfilUsuarioRequestDto perfilUsuarioDto) {
        Usuario usuario = usuarioService.findById(perfilUsuarioDto.getId_usuario());
        PerfilUsuario perfil = convertToEntity(perfilUsuarioDto);
        perfil.setUsuario(usuario);

        PerfilUsuario created = service.create(perfil);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<PerfilUsuarioResponseDto> update(@PathVariable Long id, @RequestBody PerfilUsuarioRequestDto perfilDto) {
        try{
            PerfilUsuario perfil = service.findById(id);
        } catch (Exception e) {
            return this.create(perfilDto);
        }

        PerfilUsuario perfil = convertToEntity(perfilDto);
        perfil.setId(id);
        PerfilUsuario updated = service.update(perfil, perfil.getId());

        return ResponseEntity.ok(convertToDto(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<PerfilUsuarioResponseDto> findById(@PathVariable Long id) {
        PerfilUsuario perfil = service.findById(id);
        return ResponseEntity.ok(convertToDto(perfil));
    }

    @GetMapping
    public Page<PerfilUsuarioResponseDto> listAll(Pageable pageable) {
        Page<PerfilUsuario> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private PerfilUsuarioResponseDto convertToDto(PerfilUsuario perfil){
        return mapper.map(perfil, PerfilUsuarioResponseDto.class);
    }

    private PerfilUsuario convertToEntity(@RequestBody PerfilUsuarioRequestDto perfilDto){
        return mapper.map(perfilDto, PerfilUsuario.class);
    }

}
