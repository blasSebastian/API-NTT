package cl.ntt.usercreation.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
@Schema(description = "Entidad que representa un usuario")
public class User {

    @Id
    @GeneratedValue
    @Schema(description = "ID único del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("nombre")
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String name;

    @Column(nullable = false, unique = true)
    @JsonProperty("correo")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Correo no tiene formato válido")
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@hola.com")
    private String email;

    @Column(nullable = false)
    @JsonProperty("contraseña")
    @Schema(description = "Contraseña del usuario", example = "password123")
    private String password;

    @JsonProperty("activo")
    @Schema(description = "Estado de actividad del usuario", example = "true")
    private boolean active;

    @JsonProperty("token")
    @Schema(description = "Token de autenticación del usuario", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("telefonos")
    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<Phones> phones;

    @JsonProperty("fecha_hora_creacion")
    @Schema(description = "Fecha y hora de creación del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime creationDate;

    @JsonProperty("fecha_hora_modificación")
    @Schema(description = "Fecha y hora de la última modificación del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime modifDate;

    @JsonProperty("fecha_hora_ultimo_login")
    @Schema(description = "Fecha y hora del último inicio de sesión del usuario", example = "2023-10-01T12:00:00")
    private LocalDateTime lastLogin;
}