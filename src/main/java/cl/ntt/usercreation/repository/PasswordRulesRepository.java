package cl.ntt.usercreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ntt.usercreation.entity.PasswordRules;

@Repository
public interface PasswordRulesRepository extends JpaRepository<PasswordRules, Long> {

}
