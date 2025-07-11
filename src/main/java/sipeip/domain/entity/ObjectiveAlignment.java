package sipeip.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alineaciones_objetivos")
@Builder // Lombok annotation to generate a builder for the class
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectiveAlignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alineacion")
    private Integer id;

    @Column(name = "id_objetivo_estrategico", nullable = false)
    private Integer strategicObjectiveId;

    @Column(name = "id_objetivo_pnd", nullable = false)
    private Integer pndId;

    @Column(name = "id_objetivo_ods", nullable = false)
    private Integer odsId;

    @Column(name = "id_entidad", nullable = false)
    private Integer entityId;

    @Column(name = "estado", length = 10, nullable = false)
    private String status;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime updatedAt;

    // --- Getters, Setters, Constructors (puedes usar Lombok si prefieres) ---
}
