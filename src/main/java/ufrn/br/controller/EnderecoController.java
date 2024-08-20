package ufrn.br.controller;

import ufrn.br.service.EnderecoService;
import ufrn.br.model.Endereco;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@AllArgsConstructor
public class EnderecoController {

    EnderecoService service;

    @GetMapping
    public List<Endereco> listAll() {
        return service.listAll();
    }
}