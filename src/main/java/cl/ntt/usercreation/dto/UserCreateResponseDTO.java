package cl.ntt.usercreation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response de la creación de un nuevo usuario")
public class UserCreateResponseDTO {

    @Schema(description = "ID del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @JsonProperty("creado")
    @Schema(description = "Fecha y hora de creación del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime creationDate;

    @JsonProperty("modificado")
    @Schema(description = "Fecha y hora de la última modificación del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime modifDateTime;

    @JsonProperty("ultimo_login")
    @Schema(description = "Fecha y hora del último inicio de sesión del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    @Schema(description = "Token de autenticación del usuario", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @JsonProperty("activo")
    @Schema(description = "Estado del usuario", example = "true")
    private boolean status;
}
