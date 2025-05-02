package cl.ntt.usercreation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDTO {

    @JsonProperty("correo")
    @NotBlank(message = "El correo no puede estar vacío")
    private String email;

    @JsonProperty("contraseña")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

}
