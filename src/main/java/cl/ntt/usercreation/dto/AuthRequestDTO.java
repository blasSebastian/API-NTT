package cl.ntt.usercreation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Request de la autenticación de un usuario")
public class AuthRequestDTO {

    @JsonProperty("correo")
    @NotBlank(message = "El correo no puede estar vacío")
    @Schema(description = "Correo electrónico del usuario", example = "juanperez@hoola.cl")
    private String email;

    @JsonProperty("contraseña")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña del usuario", example = "password123")
    private String password;

}
