package ufrn.br;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EletroTADSApplication {

    public static void main(String[] args) {
        SpringApplication.run(EletroTADSApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    };
}
