package ufrn.br.model;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SQLDelete(sql = "UPDATE Categoria SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank (message = "O username não pode estar em branco")
    String username;

    @NotBlank (message = "A senha não pode estar em branco")
    String senha;

    Boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_perfilUsuario")
    PerfilUsuario perfilUsuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Endereco> enderecos;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    LocalDateTime deletedAt;
}