package cl.ntt.usercreation.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.ntt.usercreation.entity.Phones;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Request para la creación de un nuevo usuario")
public class UserCreateRequestDTO {

    @JsonProperty("nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String name;

    @JsonProperty("correo")
    @NotBlank(message = "El correo no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo no tiene un formato válido")
    @Schema(description = "Correo electrónico del usuario", example = "juanperez@hola.cl")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @JsonProperty("contraseña")
    @Schema(description = "Contraseña del usuario", example = "password123")
    private String password;

    @JsonProperty("telefonos")
    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<Phones> phone;
}
