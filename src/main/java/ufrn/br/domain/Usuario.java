package ufrn.br.domain;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import ufrn.br.domain.Endereco;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@SQLDelete(sql = "UPDATE usuario SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLSelect(sql = "deleted_at is null")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Por favor, preencha o campo username.")
    private String username;

    @NotBlank(message = "Por favor, preencha o campo senha.")
    private String password;

    private boolean isAdmin = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Endereco> enderecos;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_perfilUsuario")
    private PerfilUsuario id_perfilUsuario;

    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    LocalDateTime deletedAt;
}
