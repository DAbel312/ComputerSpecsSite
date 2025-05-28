package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT id FROM User WHERE password = :password AND username = :username")
    Optional<User> findIdByUsernameAndPassword(String password, String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO \"user\" (username, password) VALUES (:username, :password)", nativeQuery = true)
    void createAdminAccount(String username, String password);

    boolean existsByUsername(String username);

}
