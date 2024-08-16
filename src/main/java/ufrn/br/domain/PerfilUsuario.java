package ufrn.br.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Por favor, preencha o campo nome.")
    private String nome;

    @NotBlank(message = "Por favor, preencha o campo gênero.")
    private String genero;

    @NotBlank(message = "Por favor, preencha o campo data de nascimento.")
    private String dataNascimento;


}
