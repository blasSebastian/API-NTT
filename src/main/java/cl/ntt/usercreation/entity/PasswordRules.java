package cl.ntt.usercreation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cl.ntt.usercreation.validation.annotation.ValidMinMax;
import cl.ntt.usercreation.validation.annotation.ValidSpecialChars;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ValidMinMax
@ValidSpecialChars
@Schema(description = "Entidad que representa las reglas de contraseña")
public class PasswordRules {

    @Id
    @Schema(hidden = true)
    @JsonIgnore
    private Long id = 1L;

    @Schema(description = "Longitud mínima de la contraseña", example = "8")
    @Min(value = 1, message = "La longitud mínima no puede ser menor a 1")
    @JsonProperty("longitud_minima")
    private int minLength;

    @Schema(description = "Longitud máxima de la contraseña", example = "20")
    @NotNull(message = "La longitud máxima no puede ser nulo")
    @JsonProperty("longitud_maxima")
    private Integer maxLength;

    @Schema(description = "Requiere al menos una letra mayúscula", example = "true")
    @NotNull(message = "El valor requiere_minuscula no puede ser nulo")
    @JsonProperty("requiere_mayuscula")
    private Boolean requireUppercase;

    @Schema(description = "Requiere al menos una letra minúscula", example = "true")
    @NotNull(message = "El valor requiere_minuscula no puede ser nulo")
    @JsonProperty("requiere_minuscula")
    private Boolean requireLowercase;

    @Schema(description = "Requiere al menos un dígito", example = "true")
    @NotNull(message = "El valor requiere_digito no puede ser nulo")
    @JsonProperty("requiere_digito")
    private Boolean requireDigit;

    @Schema(description = "Requiere al menos un carácter especial", example = "true")
    @NotNull(message = "El valor requiere_caracter_especial no puede ser nulo")
    @JsonProperty("caracter_especial")
    private Boolean requireSpecial;

    @Schema(description = "Caracteres especiales permitidos", example = "!@#$%^&*()")
    @JsonProperty("caracteres_permitidos")
    private String allowedSpecialChars;

    public String toString() {

        String auxUpperCase = requireUppercase ? "Sí" : "No";
        String auxLowerCase = requireLowercase ? "Sí" : "No";
        String auxDigit = requireDigit ? "Sí" : "No";
        String auxSpecial = requireSpecial ? "Sí" : "No";
        String auxSpecialChars = allowedSpecialChars != null ? allowedSpecialChars : "Ninguno";

        return "Longitud mínima: " + minLength + ", Longitud Máxima: " + maxLength
                + ", Requiere Mayúsculas: " + auxUpperCase + ", Requiere Minúsculas: " + auxLowerCase
                + ", Requiere números: " + auxDigit + ", Requiere Caracteres especiales: " + auxSpecial
                + ", Caracteres Especiales: " + auxSpecialChars;
    }
}
