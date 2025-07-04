package sipeip.controller;


import com.sipeip.infrastructure.input.adapter.rest.ObjectivesApi;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectivePagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sipeip.service.ObjectiveService;

@RestController
@RequiredArgsConstructor
public class ObjectiveController implements ObjectivesApi {
    private final ObjectiveService objectiveService;

    @Override
    public ResponseEntity<ObjectiveResultResponse> createObjective(ObjectiveRequest objectiveRequest) {
        return ResponseEntity
                .status(201)
                .body(objectiveService.createObjective(objectiveRequest));
    }

    @Override
    public ResponseEntity<Void> deactivateObjective(Integer id) {
        objectiveService.deactivateObjective(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ObjectivePagedResponse> getPagedObjectives(Integer page, Integer size, String name, String typeObjective, String code) {
        return new ResponseEntity<>(objectiveService.getPagedObjectives(page, size, "", "", ""), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObjectivePagedResponse> searchObjectives(Integer page, Integer size, String name, String typeObjective, String code, String type) {
        return new ResponseEntity<>(objectiveService.searchObjectives(page, size, name, typeObjective, code, type), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObjectiveResultResponse> updateObjective(Integer id, ObjectiveRequest objectiveUpdateRequest) {
        return ResponseEntity
                .status(201)
                .body(objectiveService.updateObjective(id, objectiveUpdateRequest));
    }
}
