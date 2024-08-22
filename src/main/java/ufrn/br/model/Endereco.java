package com.example.demo.domain;

import com.example.demo.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@SQLDelete(sql = "UPDATE Endereco SET deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at is null")
public class Endereco extends AbstractEntity {

    @NotBlank (message = "A rua não pode estar em braco")
    String rua;

    @NotBlank (message = "O número não pode estar em braco")
    String numero;

    @NotBlank (message = "O bairro não pode estar em braco")
    String bairro;

    String complemento;

    @NotBlank (message = "A cidade não pode estar em braco")
    String cidade;

    @NotBlank (message = "O estado não pode estar em braco")
    String uf;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
