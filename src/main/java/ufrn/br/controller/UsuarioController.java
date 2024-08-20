package ufrn.br.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ufrn.br.dto.UsuarioRequestDTO;
import ufrn.br.dto.UsuarioResponseDTO;
import ufrn.br.model.Endereco;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.model.Usuario;
import ufrn.br.service.UsuarioService;
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
@RequestMapping("/usuarios/")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    private final ModelMapper mapper;

    @GetMapping
     public Page<UsuarioResponseDTO> listAll(Pageable pageable) {
        Page<Usuario> usuariosPage = service.listAll(pageable);
        return usuariosPage.map(this::convertToDto);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO userDto){
        Usuario created = service.create(convertToEntity(userDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable("id") Long id){
        Usuario usuario = service.findById(id);
        UsuarioResponseDTO usuarioDto = mapper.map(usuario, UsuarioResponseDTO.class);

        return ResponseEntity.ok(usuarioDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        service.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@RequestBody UsuarioRequestDTO userDto, @PathVariable("id") Long id){
        try {
            Usuario usuario = service.findById(id);
        } catch (Exception e) {
            return this.create(userDto);
        }

        Usuario usuarioUpdated = service.update(mapper.map(userDto, Usuario.class), id);
        return ResponseEntity.ok(convertToDto(usuarioUpdated));
    }


    private UsuarioResponseDTO convertToDto(Usuario usuarioCreated){
        UsuarioResponseDTO usuarioResponseDto = mapper.map(usuarioCreated, UsuarioResponseDTO.class);
        usuarioResponseDto.addLinks(usuarioCreated);

        return usuarioResponseDto;
    }

    private Usuario convertToEntity(UsuarioRequestDTO userDto){
        Usuario entityUsuario = mapper.map(userDto, Usuario.class);
        PerfilUsuario entityPerfilUsuario = mapper.map(userDto.getPerfilUsuario(), PerfilUsuario.class);
        List<Endereco> entityEnderecos = userDto.getEnderecos().stream()
                                        .map(dto -> mapper.map(dto, Endereco.class))
                                       .collect(Collectors.toList());

        entityUsuario.setPerfilUsuario(entityPerfilUsuario);
        entityUsuario.setEnderecos(entityEnderecos);

        return entityUsuario;
    }
}
