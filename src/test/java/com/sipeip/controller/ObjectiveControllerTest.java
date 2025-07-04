package com.sipeip.controller;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectivePagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sipeip.controller.ObjectiveController;
import sipeip.service.ObjectiveService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObjectiveControllerTest {

    @Mock
    private ObjectiveService objectiveService;

    @InjectMocks
    private ObjectiveController objectiveController;

    @Test
    void shouldCreateObjectiveSuccessfully() {
        ObjectiveRequest request = new ObjectiveRequest("Objective Name", "Code123", "Description", "Type1", "Eje1", "ACTIVO");
        ObjectiveResultResponse response = new ObjectiveResultResponse();
        response.setResult("Objective created successfully");

        when(objectiveService.createObjective(request)).thenReturn(response);

        ResponseEntity<ObjectiveResultResponse> result = objectiveController.createObjective(request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Objective created successfully", result.getBody().getResult());
    }

    @Test
    void shouldDeactivateObjectiveSuccessfully() {
        Integer id = 1;

        doNothing().when(objectiveService).deactivateObjective(id);

        ResponseEntity<Void> result = objectiveController.deactivateObjective(id);

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(objectiveService, times(1)).deactivateObjective(id);
    }

    @Test
    void shouldReturnPagedObjectivesSuccessfully() {
        ObjectivePagedResponse response = new ObjectivePagedResponse();

        when(objectiveService.getPagedObjectives(0, 10, "", "", "")).thenReturn(response);

        ResponseEntity<ObjectivePagedResponse> result = objectiveController.getPagedObjectives(0, 10, "", "", "");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void shouldSearchObjectivesSuccessfully() {
        ObjectivePagedResponse response = new ObjectivePagedResponse();

        when(objectiveService.searchObjectives(0, 10, "name", "typeObjective", "code", "0")).thenReturn(response);

        ResponseEntity<ObjectivePagedResponse> result = objectiveController.searchObjectives(0, 10, "name", "typeObjective", "code", "0");

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void shouldUpdateObjectiveSuccessfully() {
        Integer id = 1;
        ObjectiveRequest request = new ObjectiveRequest("Updated Name", "UpdatedCode", "Updated Description", "UpdatedType", "UpdatedEje", "INACTIVO");
        ObjectiveResultResponse response = new ObjectiveResultResponse();
        response.setResult("Objective updated successfully");

        when(objectiveService.updateObjective(id, request)).thenReturn(response);

        ResponseEntity<ObjectiveResultResponse> result = objectiveController.updateObjective(id, request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Objective updated successfully", result.getBody().getResult());
    }
}