package main.java.com.library.data.repository;



import main.java.com.library.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.userName = :username")
    Optional<User> findByUsername(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.userName = :username OR u.email = :email")
    Optional<User> findByUsernameOrEmail(@Param("userName") String userName, @Param("email") String email);

    String userName(String userName);

}
