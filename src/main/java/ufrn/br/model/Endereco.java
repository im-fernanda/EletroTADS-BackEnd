package ufrn.br.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SQLDelete(sql = "UPDATE Categoria SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
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

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    LocalDateTime deletedAt;
}