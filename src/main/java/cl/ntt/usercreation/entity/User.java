package cl.ntt.usercreation.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String email;

    @Column(nullable = false)
    @JsonProperty("contraseña")
    private String password;

    @ManyToOne
    @JsonProperty("telefonos")
    private Phones phones;

    @JsonProperty("fecha_hora_creacion")
    private LocalDateTime creationDate;

    @JsonProperty("fecha_hora_modificación")
    private LocalDateTime modifDate;

    @JsonProperty("fecha_hora_ultimo_login")
    private LocalDateTime lastLogin;
}