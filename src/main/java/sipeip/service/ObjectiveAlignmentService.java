package sipeip.service;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;

public interface ObjectiveAlignmentService {
    ObjectiveResultResponse saveAlignment(ObjectiveAlignmentRequest request);
}
