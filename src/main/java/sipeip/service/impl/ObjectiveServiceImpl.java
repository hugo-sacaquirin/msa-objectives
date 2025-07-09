package sipeip.service.impl;


import com.sipeip.infrastructure.input.adapter.rest.models.ObjectivePagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sipeip.domain.entity.Objective;
import sipeip.repository.ObjectiveRepository;
import sipeip.service.ObjectiveService;
import sipeip.service.mapper.ObjectiveMapper;

import static sipeip.util.StaticValues.CREATED;

@Service
@RequiredArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveMapper objectiveMapper;

    @Override
    public ObjectiveResultResponse createObjective(ObjectiveRequest request) {
        Objective objective = objectiveRepository.save(Objective.builder()
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .objectiveType(request.getType())
                .eje(request.getEje())
                .status(request.getStatus())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build());
        if (objective.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating objective");
        }
        return getObjectiveResultResponse("Objective created successfully");
    }

    @Override
    public ObjectiveResultResponse updateObjective(Integer id, ObjectiveRequest request) {
        Objective objective = objectiveRepository.save(Objective.builder()
                .id(id)
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .objectiveType(request.getType())
                .eje(request.getEje())
                .status(request.getStatus())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build());
        if (objective.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating objective");
        }
        return getObjectiveResultResponse("Objective updated successfully");
    }

    private static ObjectiveResultResponse getObjectiveResultResponse(String message) {
        ObjectiveResultResponse entityResultResponse = new ObjectiveResultResponse();
        entityResultResponse.setCode(CREATED);
        entityResultResponse.setResult(message);
        return entityResultResponse;
    }

    @Override
    public void deactivateObjective(Integer id) {
        objectiveRepository.deleteById(id);
        if (objectiveRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting objective");
        }
    }

    @Override
    public ObjectivePagedResponse getPagedObjectives(Integer page, Integer size, String name, String typeObjective, String code) {
        ObjectivePagedResponse objectivePagedResponse = new ObjectivePagedResponse();
        objectivePagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(objectiveRepository.findAll()));
        return objectivePagedResponse;
    }

    @Override
    public ObjectivePagedResponse searchObjectives(Integer page, Integer size, String name, String typeObjective, String code, String type) {
        ObjectivePagedResponse objectivePagedResponse = new ObjectivePagedResponse();
        if (type.equals("0")) {
            objectivePagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(objectiveRepository.findByName(name)));
        } else {
            objectivePagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(objectiveRepository.findByObjectiveType(typeObjective)));
        }
        return objectivePagedResponse;
    }
}
