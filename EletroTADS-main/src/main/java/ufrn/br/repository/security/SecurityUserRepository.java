package ufrn.br.repository.security;

import org.springframework.data.repository.CrudRepository;
import ufrn.br.domain.SecurityUser;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, Long> {
    SecurityUser findByUsername(String username);
}
