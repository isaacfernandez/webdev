package webdev.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import webdev.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findUserByUsername(@Param("username") String Username);

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    User findUserByCredentials(
            @Param("username") String username,
            @Param("password") String password);
}

