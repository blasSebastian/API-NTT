package cl.ntt.usercreation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response mensajes de error")
public class ErrorResponseDTO {

    @JsonProperty("mensaje")
    @Schema(description = "Mensaje de error", example = "Usuario no encontrado")
    private String message;
}
