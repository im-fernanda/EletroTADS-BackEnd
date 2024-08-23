package ufrn.br.controller;

import ufrn.br.domain.Usuario;
import ufrn.br.dto.UsuarioRequestDTO;
import ufrn.br.dto.UsuarioResponseDTO;
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
@RequestMapping("/usuarios/")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO usuarioDto) {
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
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioDto) {
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
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok(convertToDto(usuario));
    }

    @GetMapping
    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        Page<Usuario> page = service.listAll(pageable);
        return page.map(this::convertToDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private UsuarioResponseDTO convertToDto(Usuario user){
        return mapper.map(user, UsuarioResponseDTO.class);
    }

    private Usuario convertToEntity(@RequestBody UsuarioRequestDTO userDto){
        return mapper.map(userDto, Usuario.class);
    }
}
