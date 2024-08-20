package ufrn.br.controller;

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
    public List<UsuarioResponseDTO> listAll(){
        return service.listAll().stream().map(this::convertToDto).collect(toList());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO userDto){
        Usuario created = service.create(convertToEntity(userDto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
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
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable("id") Long id, @RequestBody UsuarioRequestDTO userDto){
        try {
            Usuario usuario = service.findById(id);
        } catch (Exception e) {
            return this.create(userDto);
        }

        Usuario updated = service.update(mapper.map(userDto, Usuario.class), id);
        return ResponseEntity.ok(convertToDto(updated));
    }


    private UsuarioResponseDTO convertToDto(Usuario user){
        UsuarioResponseDTO usuarioDto = mapper.map(user, UsuarioResponseDTO.class);
        usuarioDto.addLinks(user);

        return usuarioDto;
    }

    private Usuario convertToEntity(UsuarioRequestDTO userDto){
        Usuario usuario = mapper.map(userDto, Usuario.class);
        PerfilUsuario perfilUsuario = mapper.map(userDto.getPerfilUsuario(), PerfilUsuario.class);
        List<Endereco> enderecos = userDto.getEnderecos().stream()
                .map(dto -> mapper.map(dto, Endereco.class))
                .collect(Collectors.toList());

        usuario.setPerfilUsuario(perfilUsuario);
        usuario.setEnderecos(enderecos);

        return usuario;
    }
}
