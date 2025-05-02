package cl.ntt.usercreation.validation;

import cl.ntt.usercreation.entity.PasswordRules;
import cl.ntt.usercreation.validation.annotation.ValidSpecialChars;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequireSpecialValidator implements ConstraintValidator<ValidSpecialChars, PasswordRules> {
    @Override
    public boolean isValid(PasswordRules passwordRules, ConstraintValidatorContext context) {
        if (passwordRules == null || passwordRules.getRequireSpecial() == null) {
            return true; // No valida si el objeto es nulo
        }

        // Si requireSpecial es true, caracteresPermitidos no debe ser nulo o vacío
        if (passwordRules.getRequireSpecial() &&
                (passwordRules.getAllowedSpecialChars() == null || passwordRules.getAllowedSpecialChars().isEmpty())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "El valor de caracteres_permitidos no puede ser nulo o vacío cuando requiere_caracter_especial es verdadero")
                    .addPropertyNode("caracteres_permitidos")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
