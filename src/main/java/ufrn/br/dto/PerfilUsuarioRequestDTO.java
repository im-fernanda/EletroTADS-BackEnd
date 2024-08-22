package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioRequestDto {
    String nome;
    String genero;
    String dataNascimento;
    Long id_usuario;
}
