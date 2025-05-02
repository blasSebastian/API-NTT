package cl.ntt.usercreation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ntt.usercreation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    Optional<User> findByEmailAndPassword(String email, String password);
}