package cl.ntt.usercreation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.ntt.usercreation.entity.PasswordRules;
import cl.ntt.usercreation.exception.UserCreationException;
import cl.ntt.usercreation.repository.PasswordRulesRepository;

@Service
public class PasswordRulesService {
    private static final Logger logger = LoggerFactory.getLogger(PasswordRules.class);

    @Autowired
    private PasswordRulesRepository passwordRulesRepository;

    public PasswordRules getRules() {
        return passwordRulesRepository.findById(1L)
                .orElseThrow(
                        () -> new UserCreationException("Reglas de contraseña no configuradas", HttpStatus.NOT_FOUND));
    }

    public PasswordRules updateRules(PasswordRules newRules) {
        newRules.setId(1L); // Asegurarse de que siempre se actualice el único registro
        return passwordRulesRepository.save(newRules);
    }

    private String buildRegex() {
        PasswordRules rules = getRules();
        StringBuilder regex = new StringBuilder("^");

        // Requerir al menos una letra mayúscula
        if (rules.getRequireUppercase()) {
            regex.append("(?=.*[A-Z])");
        }

        // Requerir al menos una letra minúscula
        if (rules.getRequireLowercase()) {
            regex.append("(?=.*[a-z])");
        }

        // Requerir al menos un dígito
        if (rules.getRequireDigit()) {
            regex.append("(?=.*\\d)");
        }

        // Requerir al menos un carácter especial
        if (rules.getRequireSpecial()) {
            String specialChars = rules.getAllowedSpecialChars();
            regex.append(String.format("(?=.*[%s])", specialChars));
        }

        // Longitud mínima y máxima
        regex.append(String.format(".{%d,%d}", rules.getMinLength(), rules.getMaxLength()));
        regex.append("$");

        logger.info("Regex de validación de contraseña: {}", regex.toString());
        return regex.toString();
    }

    public boolean validatePassword(String password) {
        String regex = buildRegex();
        logger.info("Validando contraseña: {}", password);
        logger.info("Regex de validación: {}", regex);
        logger.info("resultado: {}", password.matches(regex));
        return password.matches(regex);
    }
}
