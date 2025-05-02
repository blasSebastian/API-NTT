package cl.ntt.usercreation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

    @JsonProperty("fecha_hora_token")
    private LocalDateTime dateTime;
}
