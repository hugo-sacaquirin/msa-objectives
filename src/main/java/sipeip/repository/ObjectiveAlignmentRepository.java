package sipeip.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
