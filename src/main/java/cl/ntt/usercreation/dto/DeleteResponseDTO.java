package cl.ntt.usercreation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response de la eliminación de un usuario")
public class DeleteResponseDTO {

    @JsonProperty("mensaje")
    @Schema(description = "Mensaje de respuesta", example = "Usuario eliminado correctamente")
    private String message;

    @JsonProperty("usuario_id")
    @Schema(description = "ID del usuario eliminado", example = "123e4567-e89b-12d3-a456-426614174000")
    private String userId;
}
