package cl.ntt.usercreation.validation;

import cl.ntt.usercreation.entity.PasswordRules;
import cl.ntt.usercreation.validation.annotation.ValidMinMax;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinMaxValidator implements ConstraintValidator<ValidMinMax, PasswordRules> {

    @Override
    public boolean isValid(PasswordRules passwordRules, ConstraintValidatorContext context) {
        if (passwordRules == null || passwordRules.getMaxLength() == null) {
            return true;
        }
        if (passwordRules.getMinLength() > passwordRules.getMaxLength()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La longitud mínima no puede ser mayor a la longitud máxima")
                    .addPropertyNode("longitud_minima")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
