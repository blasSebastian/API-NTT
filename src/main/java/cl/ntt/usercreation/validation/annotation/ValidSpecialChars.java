package cl.ntt.usercreation.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cl.ntt.usercreation.validation.RequireSpecialValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequireSpecialValidator.class)
@Target({ ElementType.TYPE }) // Aplica a nivel de clase
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSpecialChars {
    String message() default "Si 'caracter_especial' es true, 'caracteres_permitidos' no puede ser nulo o vacío";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
