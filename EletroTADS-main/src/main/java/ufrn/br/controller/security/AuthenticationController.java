package ufrn.br.controller.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import ufrn.br.dto.security.LoginDTO;
import ufrn.br.service.security.TokenService;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final TokenService service;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String getToken(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password())
                );

        return service.generateToken(authentication);
    }
}
