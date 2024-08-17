package ufrn.br.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Por favor, preencha o campo rua.")
    private String rua;

    @NotBlank(message = "Por favor, preencha o campo numero.")
    private String numero;

    @NotBlank(message = "Por favor, preencha o campo bairro.")
    private String bairro;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @NotBlank(message = "Por favor, preencha o campo cidade.")
    private String cidade;

    @NotBlank(message = "Por favor, preencha o campo uf.")
    private String uf;
}
