package ufrn.br.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    //@NotBlank(message = "Por favor, preencha o campo username.")
    private String username;

    //@NotBlank(message = "Por favor, preencha o campo senha.")
    private String password;

    private boolean isAdmin = false;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Endereco> enderecos;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_perfilUsuario")
    private PerfilUsuario id_perfilUsuario;
}
