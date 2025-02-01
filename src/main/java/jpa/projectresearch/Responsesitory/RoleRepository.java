package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Entity.Role;
import jpa.projectresearch.Variable.Variable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName (Variable.AppRole role);


}
