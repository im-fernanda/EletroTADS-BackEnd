package ufrn.br.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.Set;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@SQLDelete(sql = "UPDATE Produto SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Por favor, preencha o campo username.")
    private String nome;

    @NotBlank(message = "Por favor, preencha o campo descrição.")
    private String descricao;

    @NotBlank(message = "Por favor, preencha o campo preço.")
    private int preco;

    @Min(value = 0, message = "O estoque precisa ser maior que 0.")
    @NotBlank(message = "Por favor, preencha o campo estoque.")
    private int estoque;

    @ManyToMany
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categoria;

    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

}
