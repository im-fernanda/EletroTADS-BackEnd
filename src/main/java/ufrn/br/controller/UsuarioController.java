package com.example.demo.controller;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioRequestDto;
import com.example.demo.dto.UsuarioResponseDto;
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
@RequestMapping("/usuarios/")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioRequestDto usuarioDto) {
        System.out.println("nome: " + usuarioDto.getUsername());
        Usuario usuario = convertToEntity(usuarioDto);
        System.out.println(usuario.getUsername());
        Usuario created = service.create(usuario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioDto) {
        try{
            Usuario usuario = service.findById(id);
        } catch (Exception e) {
            return this.create(usuarioDto);
        }

        Usuario usuario = convertToEntity(usuarioDto);
        usuario.setId(id);
        Usuario updated = service.update(usuario, usuario.getId());

        return ResponseEntity.ok(convertToDto(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok(convertToDto(usuario));
    }

    @GetMapping
    public Page<UsuarioResponseDto> findAll(Pageable pageable) {
        Page<Usuario> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private UsuarioResponseDto convertToDto(Usuario user){
        return mapper.map(user, UsuarioResponseDto.class);
    }

    private Usuario convertToEntity(@RequestBody UsuarioRequestDto userDto){
        return mapper.map(userDto, Usuario.class);
    }
}
