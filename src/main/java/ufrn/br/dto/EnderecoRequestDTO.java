package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDto {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;
    Long id_usuario;
}
