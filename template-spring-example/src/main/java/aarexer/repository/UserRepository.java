package aarexer.repository;

import aarexer.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findByActivationCode(String code);
}
