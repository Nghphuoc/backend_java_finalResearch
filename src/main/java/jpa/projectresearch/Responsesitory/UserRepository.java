package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail (String email);

    Optional<User> findByFullName(String fullname);

    Boolean existsByFullName(String username);

    Boolean existsByEmail(String email);
}
