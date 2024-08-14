package ufrn.br.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank (message = "O nome não pode estar em branco.")
    String nome;
    @Min(value = 0, message = "A idade tem que ser maior que zero.")
    int idade;
    String dataNascimento;
    String sexo;
    boolean isAdmin = false;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    Endereco endereco;
}
