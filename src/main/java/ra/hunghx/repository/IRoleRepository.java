package ra.hunghx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.hunghx.entity.RoleName;
import ra.hunghx.entity.Roles;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRoleName(RoleName roleName);
}
