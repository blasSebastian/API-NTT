package cl.ntt.usercreation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteResponseDTO {

    @JsonProperty("mensaje")
    private String message;

    @JsonProperty("usuario_id")
    private String userId;
}
