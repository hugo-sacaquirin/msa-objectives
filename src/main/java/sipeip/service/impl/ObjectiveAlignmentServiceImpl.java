package sipeip.service.impl;

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

import java.time.LocalDateTime;

import static sipeip.util.StaticValues.getObjectiveResultResponse;

@Service
@RequiredArgsConstructor
public class ObjectiveAlignmentServiceImpl implements ObjectiveAlignmentService {
    private final ObjectiveAlignmentRepository alignmentRepository;

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
}
