package ufrn.br.model;


import ufrn.br.model.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@SQLDelete(sql = "UPDATE Usuario SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Usuario extends AbstractEntity {

    @NotBlank (message = "O username não pode estar em branco")
    String username;

    @NotBlank (message = "A senha não pode estar em branco")
    String senha;

    Boolean isAdmin = false;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    PerfilUsuario perfilUsuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    List<Endereco> enderecos = new ArrayList<>();
}
