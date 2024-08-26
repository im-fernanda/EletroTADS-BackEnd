package ufrn.br.controller;

import ufrn.br.domain.Endereco;
import ufrn.br.domain.Usuario;
import ufrn.br.dto.EnderecoRequestDTO;
import ufrn.br.dto.EnderecoResponseDTO;
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
@RequestMapping("/enderecos/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class EnderecoController {

    private final EnderecoService service;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    /*@PostMapping
    public ResponseEntity<EnderecoResponseDTO> create(@RequestBody EnderecoRequestDTO enderecoDto) {
        Usuario usuario = usuarioService.findById(enderecoDto.getId_usuario());
        Endereco endereco = convertToEntity(enderecoDto);
        endereco.setUsuario(usuario);

        Endereco created = service.create(endereco);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(convertToDTO(created));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> update(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoDto) {
        try{
            Endereco endereco = service.findById(id);
        } catch (Exception e) {
            Endereco created = service.create(convertToEntity(enderecoDto));
            return ResponseEntity.ok(convertToDTO(created));

        }

        Endereco endereco = convertToEntity(enderecoDto);
        endereco.setId(id);
        Endereco updated = service.update(endereco, endereco.getId());

        return ResponseEntity.ok(convertToDTO(updated));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoResponseDTO> findById(@PathVariable Long id) {
        Endereco endereco = service.findById(id);
        return ResponseEntity.ok(convertToDTO(endereco));
    }

    @GetMapping
    public Page<EnderecoResponseDTO> listAll(Pageable pageable) {
        Page<Endereco> page = service.listAll(pageable);
        return page.map(this::convertToDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private EnderecoResponseDTO convertToDTO(Endereco endereco){
        EnderecoResponseDTO enderecoDto = mapper.map(endereco, EnderecoResponseDTO.class);
        enderecoDto.addLinks(endereco);
        return enderecoDto;
    }

    private Endereco convertToEntity(@RequestBody EnderecoRequestDTO enderecoDto){
        return mapper.map(enderecoDto, Endereco.class);
    }
}
