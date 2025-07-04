package sipeip.service.mapper;


import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResponse;
import org.springframework.stereotype.Component;
import sipeip.domain.entity.Objective;

import java.util.List;

@Component
public class ObjectiveMapper {

    public ObjectiveResponse toObjectiveResponse(Objective objective) {
        if (objective == null) {
            return null;
        }
        ObjectiveResponse objectiveResponse = new ObjectiveResponse();
        objectiveResponse.setId(objective.getId());
        objectiveResponse.setName(objective.getName());
        objectiveResponse.setCode(objective.getCode());
        objectiveResponse.setDescription(objective.getDescription());
        objectiveResponse.setType(objective.getObjectiveType());
        objectiveResponse.setEje(objective.getEje());
        objectiveResponse.setStatus(objective.getStatus());
        objectiveResponse.setCreatedAt(String.valueOf(objective.getCreatedAt()));
        objectiveResponse.setUpdatedAt(String.valueOf(objective.getUpdatedAt()));
        return objectiveResponse;
    }

    public List<ObjectiveResponse> toObjectiveResponseFromObjective(List<Objective> entitiesList) {
        return entitiesList.stream()
                .map(this::toObjectiveResponse)
                .toList();
    }
}