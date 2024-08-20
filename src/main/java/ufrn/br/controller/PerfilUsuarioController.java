package ufrn.br.controller;

import ufrn.br.dto.PerfilUsuarioRequestDTO;
import ufrn.br.dto.PerfilUsuarioResponseDTO;
import ufrn.br.model.PerfilUsuario;
import ufrn.br.model.Usuario;
import ufrn.br.service.PerfilUsuarioService;
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
@RequestMapping("/perfil/")
@AllArgsConstructor
public class PerfilUsuarioController {
    private final PerfilUsuarioService service;
    private final ModelMapper mapper;
    
    
}
