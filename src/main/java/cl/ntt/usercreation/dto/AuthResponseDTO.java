package cl.ntt.usercreation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response de la autenticación de un usuario")
public class AuthResponseDTO {

    @Schema(description = "ID del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private String token;

    @JsonProperty("fecha_hora_token")
    @Schema(description = "Fecha y hora de creación del token", example = "2023-10-01T12:00:00")
    private LocalDateTime dateTime;
}
