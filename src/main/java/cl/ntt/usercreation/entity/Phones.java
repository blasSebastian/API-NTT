package cl.ntt.usercreation.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Phones {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @JsonProperty("numero")
    private int number;

    @Column(nullable = false)
    @JsonProperty("codigo_ciudad")
    private int cityCode;

    @Column(nullable = false)
    @JsonProperty("codigo_pais")
    private int countryCode;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference
    private User user;

}
