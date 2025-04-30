package cl.ntt.usercreation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ntt.usercreation.entity.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, UUID> {
}