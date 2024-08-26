package ufrn.br;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ufrn.br.core.security.RsaKeyProperties;
import ufrn.br.domain.Endereco;
import ufrn.br.domain.Usuario;
import ufrn.br.domain.SecurityUser;
import ufrn.br.repository.security.SecurityUserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class EletroTADSApplication {

    public static void main(String[] args) {
        SpringApplication.run(EletroTADSApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    };

    @Autowired
    SecurityUserRepository securityUserRerpository;

    @Autowired
    BCryptPasswordEncoder encoder;


    @PostConstruct
    public void started() {
        Usuario u = new Usuario();

        u.setUsername("Luan");
        u.setSenha("123");
        u.setIsAdmin(true);

        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsuario(u);
        securityUser.setUsername("admin");
        securityUser.setSenha(encoder.encode("admin"));

        securityUserRerpository.save(securityUser);
    }
}
