package ufrn.br.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ufrn.br.domain.Endereco;
import ufrn.br.domain.PerfilUsuario;
import ufrn.br.domain.SecurityUser;
import ufrn.br.domain.Usuario;
import ufrn.br.dto.*;
import ufrn.br.repository.security.SecurityUserRepository;
import ufrn.br.service.EnderecoService;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioController {

    private final SecurityUserRepository securityRepository;
    private final BCryptPasswordEncoder encoder;
    private final UsuarioService service;
    private final EnderecoService enderecoService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO usuarioDto) {
        System.out.println("nome: " + usuarioDto.getUsername());
        Usuario usuario = convertToEntity(usuarioDto);
        System.out.println(usuario.getUsername());
        Usuario created = service.create(usuario);

        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsuario(created);
        securityUser.setUsername(created.getUsername());
        securityUser.setSenha(encoder.encode(created.getSenha()));

        securityRepository.save(securityUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(created));
    }

    @PostMapping("{id}/enderecos/")
    public ResponseEntity<UsuarioResponseDTO> addEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoDto) {
        Endereco endereco = mapper.map(enderecoDto, Endereco.class);
        Usuario usuario = service.findById(id);
        endereco.setUsuario(usuario);
        usuario.setId(id);
        usuario.getEnderecos().add(endereco);

        Usuario updated = service.update(usuario, usuario.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(updated));
    }

    @PostMapping("{id}/perfil/")
    public ResponseEntity<UsuarioResponseDTO> addPerfil(@PathVariable Long id, @RequestBody PerfilUsuarioRequestDTO perfilDto) {
        PerfilUsuario perfil = mapper.map(perfilDto, PerfilUsuario.class);
        Usuario usuario = service.findById(id);
        perfil.setUsuario(usuario);
        usuario.setId(id);
        usuario.setPerfilUsuario(perfil);

        Usuario updated = service.update(usuario, usuario.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(updated));
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

    @GetMapping("{id}/enderecos/")
    public Page<EnderecoResponseDTO> findAllEnderecos(@PathVariable Long id, Pageable pageable) {
        Page<Endereco> page = enderecoService.listAllEnderecos(id, pageable);
        return page.map(this::convertToDtoEndereco);
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
        UsuarioResponseDTO usuarioDto = mapper.map(user, UsuarioResponseDTO.class);
        usuarioDto.addLinks(user);
        return usuarioDto;
    }

    private EnderecoResponseDTO convertToDtoEndereco(Endereco endereco){
        EnderecoResponseDTO enderecoDto = mapper.map(endereco, EnderecoResponseDTO.class);
        enderecoDto.addLinks(endereco);
        return enderecoDto;
    }

    private Usuario convertToEntity(@RequestBody UsuarioRequestDTO userDto){
        return mapper.map(userDto, Usuario.class);
    }
}
