package ufrn.br.dto;

import lombok.Data;

@Data
public class EnderecoRequestDTO {
    String rua;
    String numero;
    String bairro;
    String complemento;
    String cidade;
    String uf;


}
