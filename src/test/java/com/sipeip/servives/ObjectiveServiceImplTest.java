package com.sipeip.servives;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectivePagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import sipeip.domain.State;
import sipeip.domain.entity.Objective;
import sipeip.repository.ObjectiveRepository;
import sipeip.service.impl.ObjectiveServiceImpl;
import sipeip.service.mapper.ObjectiveMapper;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveServiceImplTest {

    @Mock
    private ObjectiveRepository objectiveRepository;

    @Mock
    private ObjectiveMapper objectiveMapper;

    @InjectMocks
    private ObjectiveServiceImpl objectiveService;

    @Test
    void shouldCreateObjectiveSuccessfully() {
        ObjectiveRequest request = new ObjectiveRequest("Objective Name", "Code123", "Description", "Type1", "Eje1", "ACTIVO");
        Objective savedObjective = Objective.builder()
                .id(1)
                .name("Objective Name")
                .code("Code123")
                .description("Description")
                .objectiveType("Type1")
                .eje("Eje1")
                .status(State.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(objectiveRepository.save(any(Objective.class))).thenReturn(savedObjective);

        ObjectiveResultResponse response = objectiveService.createObjective(request);

        assertNotNull(response);
        assertEquals("Objective created successfully", response.getResult());
    }

    @Test
    void shouldThrowExceptionWhenCreateObjectiveFails() {
        ObjectiveRequest request = new ObjectiveRequest("Objective Name", "Code123", "Description", "Type1", "Eje1", "ACTIVO");

        when(objectiveRepository.save(any(Objective.class))).thenReturn(Objective.builder().id(null).build());

        assertThrows(ResponseStatusException.class, () -> objectiveService.createObjective(request));
    }

    @Test
    void shouldUpdateObjectiveSuccessfully() {
        Integer id = 1;
        ObjectiveRequest request = new ObjectiveRequest("Updated Name", "UpdatedCode", "Updated Description", "UpdatedType", "UpdatedEje", "ACTIVO");
        Objective updatedObjective = Objective.builder()
                .id(id)
                .name("Updated Name")
                .code("UpdatedCode")
                .description("Updated Description")
                .objectiveType("UpdatedType")
                .eje("UpdatedEje")
                .status(State.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(objectiveRepository.save(any(Objective.class))).thenReturn(updatedObjective);

        ObjectiveResultResponse response = objectiveService.updateObjective(id, request);

        assertNotNull(response);
        assertEquals("Objective updated successfully", response.getResult());
    }

    @Test
    void shouldThrowExceptionWhenUpdateObjectiveFails() {
        Integer id = 1;
        ObjectiveRequest request = new ObjectiveRequest("Updated Name", "UpdatedCode", "Updated Description", "UpdatedType", "UpdatedEje", "ACTIVO");

        when(objectiveRepository.save(any(Objective.class))).thenReturn(Objective.builder().id(null).build());

        assertThrows(ResponseStatusException.class, () -> objectiveService.updateObjective(id, request));
    }

    @Test
    void shouldDeactivateObjectiveSuccessfully() {
        Integer id = 1;

        doNothing().when(objectiveRepository).deleteById(id);
        when(objectiveRepository.existsById(id)).thenReturn(false);

        assertDoesNotThrow(() -> objectiveService.deactivateObjective(id));
    }

    @Test
    void shouldThrowExceptionWhenDeactivateObjectiveFails() {
        Integer id = 1;

        doNothing().when(objectiveRepository).deleteById(id);
        when(objectiveRepository.existsById(id)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> objectiveService.deactivateObjective(id));
    }

    @Test
    void shouldReturnPagedObjectives() {
        when(objectiveRepository.findAll()).thenReturn(Collections.emptyList());
        when(objectiveMapper.toObjectiveResponseFromObjective(anyList())).thenReturn(Collections.emptyList());

        ObjectivePagedResponse response = objectiveService.getPagedObjectives(0, 10, null, null, null);

        assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
    }

    @Test
    void shouldReturnObjectivesByName() {
        String name = "Objective Name";
        when(objectiveRepository.findByName(name)).thenReturn(Collections.emptyList());
        when(objectiveMapper.toObjectiveResponseFromObjective(anyList())).thenReturn(Collections.emptyList());

        ObjectivePagedResponse response = objectiveService.searchObjectives(0, 10, name, null, null, "0");

        assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
    }

    @Test
    void shouldReturnObjectivesByType() {
        String type = "Type1";
        when(objectiveRepository.findByObjectiveType(type)).thenReturn(Collections.emptyList());
        when(objectiveMapper.toObjectiveResponseFromObjective(anyList())).thenReturn(Collections.emptyList());

        ObjectivePagedResponse response = objectiveService.searchObjectives(0, 10, null, null, type, "1");

        assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
    }
}