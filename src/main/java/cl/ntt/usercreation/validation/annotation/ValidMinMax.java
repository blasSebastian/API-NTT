package cl.ntt.usercreation.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cl.ntt.usercreation.validation.MinMaxValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MinMaxValidator.class)
@Target({ ElementType.TYPE }) // Aplica a nivel de clase
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMinMax {
    String message() default "La longitud mínima debe ser menor o igual a la longitud máxima";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
