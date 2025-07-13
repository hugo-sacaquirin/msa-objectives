package sipeip.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sipeip.domain.dto.ObjectiveAlignmentView;
import sipeip.domain.entity.ObjectiveAlignment;

import java.util.List;
import java.util.Optional;

public interface ObjectiveAlignmentRepository extends JpaRepository<ObjectiveAlignment, Integer> {

    // Validar unicidad (para el POST)
    Optional<ObjectiveAlignment> findByStrategicObjectiveIdAndPndIdAndOdsIdAndEntityId(
            Integer strategicObjectiveId, Integer pndId, Integer odsId, Integer entityId);


    // Filtros para /search
    List<ObjectiveAlignment> findByStrategicObjectiveId(Integer strategicObjectiveId, Pageable pageable);

    List<ObjectiveAlignment> findByPndId(Integer pndId, Pageable pageable);

    List<ObjectiveAlignment> findByOdsId(Integer odsId, Pageable pageable);

    List<ObjectiveAlignment> findByEntityId(Integer entityId, Pageable pageable);

    @Query(value = """
    SELECT
      ao.id_alineacion AS alignmentId,
      oe.id_objetivo AS strategicObjectiveId, oe.nombre AS strategicObjectiveName, oe.tipo_objetivo AS strategicObjectiveType,
      opnd.id_objetivo AS pndObjectiveId, opnd.nombre AS pndObjectiveName,
      oods.id_objetivo AS odsObjectiveId, oods.nombre AS odsObjectiveName,
      e.id_entidad AS entityId, e.nombre AS entityName,
      ao.estado AS status, ao.fecha_creacion AS createdAt, ao.fecha_actualizacion AS updatedAt
    FROM alineaciones_objetivos ao
    JOIN objetivos oe ON ao.id_objetivo_estrategico = oe.id_objetivo
    JOIN objetivos opnd ON ao.id_objetivo_pnd = opnd.id_objetivo
    JOIN objetivos oods ON ao.id_objetivo_ods = oods.id_objetivo
    JOIN entidades e ON ao.id_entidad = e.id_entidad
    WHERE
      (:tipoObjetivo IS NULL OR oe.tipo_objetivo ILIKE %:tipoObjetivo%)
      OR
      (:nombreEntidad IS NULL OR e.nombre ILIKE %:nombreEntidad%)
    ORDER BY ao.fecha_creacion DESC
    """, nativeQuery = true)
    List<ObjectiveAlignmentView> searchAlignments(
            @Param("tipoObjetivo") String objectiveType,
            @Param("nombreEntidad") String nameEntity
    );
}
