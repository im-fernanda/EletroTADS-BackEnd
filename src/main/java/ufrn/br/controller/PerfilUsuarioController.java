package ufrn.br.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ufrn.br.dto.PerfilUsuarioRequestDTO;
import ufrn.br.dto.PerfilUsuarioResponseDTO;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.service.PerfilUsuarioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper;

    @GetMapping
    public Page<PerfilUsuarioResponseDTO> listAll(Pageable pageable) {
        Page<PerfilUsuario> perfilUsuariosPage = service.listAll(pageable);
        return perfilUsuariosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<PerfilUsuarioResponseDTO> create(@RequestBody PerfilUsuarioRequestDTO userDto){
        PerfilUsuario created = service.create(convertToEntity(userDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<PerfilUsuarioResponseDTO> getById(@PathVariable("id") Long id){
        PerfilUsuario perfilUsuario = service.findById(id);
        PerfilUsuarioResponseDTO perfilUsuarioDto = mapper.map(perfilUsuario, PerfilUsuarioResponseDTO.class);

        return ResponseEntity.ok(perfilUsuarioDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<PerfilUsuarioResponseDTO> update(@RequestBody PerfilUsuarioRequestDTO perfilUserDto, @PathVariable("id") Long id){
        try {
            PerfilUsuario perfilUsuario = service.findById(id);
        } catch (Exception e) {
            return this.create(perfilUserDto);
        }

        PerfilUsuario perfilUsuarioUpdated = service.update(mapper.map(perfilUserDto, PerfilUsuario.class), id);
        return ResponseEntity.ok(convertToDto(perfilUsuarioUpdated));
    }

    private PerfilUsuarioResponseDTO convertToDto(PerfilUsuario perfilUsuarioCreated){
        PerfilUsuarioResponseDTO perfilUsuarioResponseDto = mapper.map(perfilUsuarioCreated, PerfilUsuarioResponseDTO.class);
        perfilUsuarioResponseDto.addLinks(perfilUsuarioCreated);

        return perfilUsuarioResponseDto;
    }

    private PerfilUsuario convertToEntity(PerfilUsuarioRequestDTO perfilUserDto){
        PerfilUsuario entityPerfilUsuario = mapper.map(perfilUserDto, PerfilUsuario.class);

        return entityPerfilUsuario;
    }
}