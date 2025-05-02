package cl.ntt.usercreation.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
@Schema(description = "Entidad que representa un teléfono asociado a un usuario")
public class Phones {

    @Id
    @GeneratedValue
    @Schema(description = "ID único del teléfono", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("numero")
    @Schema(description = "Número de teléfono", example = "123456789")
    private int number;

    @Column(nullable = false)
    @JsonProperty("codigo_ciudad")
    @Schema(description = "Código de ciudad del teléfono", example = "2")
    private int cityCode;

    @Column(nullable = false)
    @JsonProperty("codigo_pais")
    @Schema(description = "Código de país del teléfono", example = "56")
    private int countryCode;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference
    @Schema(hidden = true)
    private User user;

}
