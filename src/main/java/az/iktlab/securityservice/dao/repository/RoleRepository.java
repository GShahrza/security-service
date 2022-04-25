package az.iktlab.securityservice.dao.repository;

import az.iktlab.securityservice.dao.entity.ERole;
import az.iktlab.securityservice.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole roleName);
}
