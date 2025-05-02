package cl.ntt.usercreation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private UUID id;

    @JsonProperty("creado")
    private LocalDateTime creationDate;

    @JsonProperty("modificado")
    private LocalDateTime modifDateTime;

    @JsonProperty("ultimo_login")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("activo")
    private boolean status;
}
