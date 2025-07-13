package sipeip.service.mapper;


import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentResponse;
import org.springframework.stereotype.Component;
import sipeip.domain.dto.ObjectiveAlignmentView;

import java.util.List;

@Component
public class ObjectiveAlignmentMapper {

    public ObjectiveAlignmentResponse toObjectiveResponse(ObjectiveAlignmentView objective) {
        if (objective == null) {
            return null;
        }
        ObjectiveAlignmentResponse objectiveResponse = new ObjectiveAlignmentResponse();
        objectiveResponse.setId(objective.getAlignmentId());
        objectiveResponse.setStrategicObjective(objective.getStrategicObjectiveName());
        objectiveResponse.setOds(objective.getOdsObjectiveName());
        objectiveResponse.setPnd(objective.getPndObjectiveName());
        objectiveResponse.setEntity(objective.getEntityName());
        objectiveResponse.setStatus(objective.getStatus());
        objectiveResponse.setCreatedAt(String.valueOf(objective.getCreatedAt()));
        objectiveResponse.setUpdatedAt(String.valueOf(objective.getUpdatedAt()));
        return objectiveResponse;
    }

    public List<ObjectiveAlignmentResponse> toObjectiveResponseFromObjective(List<ObjectiveAlignmentView> entitiesList) {
        return entitiesList.stream()
                .map(this::toObjectiveResponse)
                .toList();
    }
}