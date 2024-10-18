package ra.hunghx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.hunghx.entity.Users;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {
    @Query("from Users where email = :email")
    Optional<Users> findByEmail(@Param("email") String email);
}
