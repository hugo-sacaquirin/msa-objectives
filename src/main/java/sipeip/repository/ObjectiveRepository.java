package sipeip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sipeip.domain.entity.Objective;

import java.util.List;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Integer> {
    List<Objective> findByObjectiveType(String objectiveType);

    List<Objective> findByName(String name);
}