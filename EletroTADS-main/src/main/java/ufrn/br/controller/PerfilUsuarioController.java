package ufrn.br.controller;

import ufrn.br.dto.PerfilUsuarioRequestDTO;
import ufrn.br.dto.PerfilUsuarioResponseDTO;
import ufrn.br.domain.PerfilUsuario;
import ufrn.br.domain.Usuario;
import ufrn.br.service.PerfilUsuarioService;
import ufrn.br.service.UsuarioService;
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
    public ResponseEntity<PerfilUsuarioResponseDTO> create(@RequestBody PerfilUsuarioRequestDTO perfilUsuarioDto) {
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
    public ResponseEntity<PerfilUsuarioResponseDTO> update(@PathVariable Long id, @RequestBody PerfilUsuarioRequestDTO perfilDto) {
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
    public ResponseEntity<PerfilUsuarioResponseDTO> findById(@PathVariable Long id) {
        PerfilUsuario perfil = service.findById(id);
        return ResponseEntity.ok(convertToDto(perfil));
    }

    @GetMapping
    public Page<PerfilUsuarioResponseDTO> listAll(Pageable pageable) {
        Page<PerfilUsuario> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private PerfilUsuarioResponseDTO convertToDto(PerfilUsuario perfil){
        return mapper.map(perfil, PerfilUsuarioResponseDTO.class);
    }

    private PerfilUsuario convertToEntity(@RequestBody PerfilUsuarioRequestDTO perfilDto){
        return mapper.map(perfilDto, PerfilUsuario.class);
    }

}
