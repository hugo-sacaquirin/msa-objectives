package sipeip.service.impl;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentPagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveAlignmentRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sipeip.domain.State;
import sipeip.repository.ObjectiveAlignmentRepository;
import sipeip.service.ObjectiveAlignmentService;
import sipeip.service.mapper.ObjectiveAlignmentMapper;

import java.time.LocalDateTime;

import static sipeip.util.StaticValues.getObjectiveResultResponse;

@Service
@RequiredArgsConstructor
public class ObjectiveAlignmentServiceImpl implements ObjectiveAlignmentService {
    private final ObjectiveAlignmentRepository alignmentRepository;
    private final ObjectiveAlignmentMapper objectiveMapper;

    @Transactional
    @Override
    public ObjectiveResultResponse saveAlignment(ObjectiveAlignmentRequest request) {
        // Verificar unicidad
        boolean exists = alignmentRepository
                .findByStrategicObjectiveIdAndPndIdAndOdsIdAndEntityId(
                        request.getStrategicObjectiveId(),
                        request.getPndId(),
                        request.getOdsId(),
                        request.getEntityId()
                ).isPresent();

        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate alignment");
        }

        // Guardar alineación
        sipeip.domain.entity.ObjectiveAlignment alignment = sipeip.domain.entity.ObjectiveAlignment.builder()
                .strategicObjectiveId(request.getStrategicObjectiveId())
                .pndId(request.getPndId())
                .odsId(request.getOdsId())
                .entityId(request.getEntityId())
                .status(State.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();

        alignment = alignmentRepository.save(alignment);
        if (alignment.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating objective");
        }
        return getObjectiveResultResponse("Alignment Objective created successfully");
    }

    @Override
    public void deactivateAlignmentObjective(Integer id) {
        alignmentRepository.deleteById(id);
        if (alignmentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting Alignment objective");
        }
    }

    @Override
    public ObjectiveAlignmentPagedResponse searchAlignmentObjectives(Integer page, Integer size, String entity, String typeObjective, String type) {
        ObjectiveAlignmentPagedResponse objectiveAlignmentPagedResponse = new ObjectiveAlignmentPagedResponse();
        if (type.equals("0")) {
            objectiveAlignmentPagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(alignmentRepository.searchAlignments(typeObjective, " ")));
        } else {
            objectiveAlignmentPagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(alignmentRepository.searchAlignments(" ", entity)));
        }
        return objectiveAlignmentPagedResponse;
    }

    @Override
    public ObjectiveAlignmentPagedResponse getPagedAlignmentObjectives(Integer page, Integer size) {
        ObjectiveAlignmentPagedResponse objectiveAlignmentPagedResponse = new ObjectiveAlignmentPagedResponse();
        objectiveAlignmentPagedResponse.setContent(objectiveMapper.toObjectiveResponseFromObjective(alignmentRepository.searchAlignments("", "")));
        return objectiveAlignmentPagedResponse;
    }
}
