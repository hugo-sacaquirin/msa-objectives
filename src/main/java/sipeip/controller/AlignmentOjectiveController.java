package sipeip.controller;

import com.sipeip.infrastructure.input.adapter.rest.ObjectiveAlignmentsApi;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentHierarchicalResponseInner;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentPagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sipeip.service.ObjectiveAlignmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlignmentOjectiveController implements ObjectiveAlignmentsApi {
    private final ObjectiveAlignmentService objectiveAlignmentService;

    @Override
    public ResponseEntity<ObjectiveResultResponse> createObjectiveAlignment(ObjectiveAlignmentRequest objectiveAlignmentRequest) {
        return ResponseEntity
                .status(201)
                .body(objectiveAlignmentService.saveAlignment(objectiveAlignmentRequest));
    }

    @Override
    public ResponseEntity<Void> deactivateObjectiveAlignment(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ObjectiveAlignmentHierarchicalResponseInner>> getHierarchicalObjectiveAlignments() {
        return null;
    }

    @Override
    public ResponseEntity<ObjectiveAlignmentPagedResponse> getPagedObjectiveAlignments(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<ObjectiveAlignmentPagedResponse> searchObjectiveAlignments(Integer page, Integer size, String strategicObjectiveName, String pndName, String odsName, String entityName) {
        return null;
    }
}
