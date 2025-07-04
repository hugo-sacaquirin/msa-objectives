package sipeip.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "objetivos")
@Builder // Lombok annotation to generate a builder for the class
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo")
    private Integer id;
    @Column(name = "codigo", nullable = false, length = 50)
    private String code;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String description;

    @Column(name = "tipo_objetivo", nullable = false, length = 50)
    private String objectiveType;

    @Column(name = "eje", length = 100)
    private String eje;

    @Column(name = "estado", nullable = false, length = 10)
    private String status;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime updatedAt;
}
