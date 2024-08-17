package ufrn.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;
    UsuarioRequestDTO usuario;
}
