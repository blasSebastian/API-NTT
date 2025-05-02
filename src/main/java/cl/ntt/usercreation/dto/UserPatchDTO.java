package cl.ntt.usercreation.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.ntt.usercreation.entity.Phones;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPatchDTO {

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("correo")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo no tiene un formato válido")
    private String email;

    @JsonProperty("contraseña")
    private String password;

    @JsonProperty("activo")
    private Boolean active;

    @JsonProperty("telefonos")
    @Valid
    private List<Phones> phones;
}
