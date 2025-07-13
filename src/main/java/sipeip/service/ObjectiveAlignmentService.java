package sipeip.service;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentPagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;

public interface ObjectiveAlignmentService {
    ObjectiveResultResponse saveAlignment(ObjectiveAlignmentRequest request);

    void deactivateAlignmentObjective(Integer id);

    ObjectiveAlignmentPagedResponse searchAlignmentObjectives(Integer page, Integer size, String entity, String typeObjective, String type);

    ObjectiveAlignmentPagedResponse getPagedAlignmentObjectives(Integer page, Integer size);

}
