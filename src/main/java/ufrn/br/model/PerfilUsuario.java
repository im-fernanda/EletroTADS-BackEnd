package ufrn.br.model;

import ufrn.br.model.generic.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@SQLDelete(sql = "UPDATE perfilUsuario SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class PerfilUsuario extends AbstractEntity {

    @NotBlank (message = "O nome não pode estar em branco")
    String nome;

    String genero;

    @NotBlank (message = "A data de nascimento não pode estar em branco")
    String dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;
}
