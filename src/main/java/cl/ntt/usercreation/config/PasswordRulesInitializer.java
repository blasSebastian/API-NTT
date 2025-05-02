package cl.ntt.usercreation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.ntt.usercreation.entity.PasswordRules;
import cl.ntt.usercreation.repository.PasswordRulesRepository;

@Component
public class PasswordRulesInitializer implements CommandLineRunner {

    @Autowired
    private PasswordRulesRepository passwordRulesRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!passwordRulesRepository.existsById(1L)) {
            PasswordRules defaultRules = new PasswordRules();
            defaultRules.setMinLength(8);
            defaultRules.setMaxLength(20);
            defaultRules.setRequireUppercase(true);
            defaultRules.setRequireLowercase(true);
            defaultRules.setRequireDigit(true);
            defaultRules.setRequireSpecial(true);
            defaultRules.setAllowedSpecialChars("!@#$%^&*()_+");
            passwordRulesRepository.save(defaultRules);
        }
    }
}
