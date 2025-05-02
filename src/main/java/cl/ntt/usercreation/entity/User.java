package cl.ntt.usercreation.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("nombre")
    private String name;

    @Column(nullable = false, unique = true)
    @JsonProperty("correo")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo no tiene un formato válido")
    private String email;

    @Column(nullable = false)
    @JsonProperty("contraseña")
    private String password;

    @JsonProperty("activo")
    @Column
    private boolean active;

    @JsonProperty("token")
    @Column
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("telefonos")
    private List<Phones> phones;

    @JsonProperty("fecha_hora_creacion")
    private LocalDateTime creationDate;

    @JsonProperty("fecha_hora_modificación")
    private LocalDateTime modifDate;

    @JsonProperty("fecha_hora_ultimo_login")
    private LocalDateTime lastLogin;
}