package ufrn.br.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLSelect;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE produto SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLSelect(sql = "deleted_at is null")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Por favor, preencha o campo nome.")
    private String nome;

    @NotBlank(message = "Por favor, preencha o campo descricao")
    private String descricao;

    @NotBlank(message = "Por favor, preencha o campo preco.")
    private float preco;

    @Min(value = 0, message = "O estoque precisa ser maior que 0.")
    @NotBlank(message = "Por favor, preencha o campo estoque.")
    private int estoque;

    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;

    LocalDateTime deletedAt;

}
