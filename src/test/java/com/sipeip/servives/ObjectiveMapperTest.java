package com.sipeip.servives;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResponse;
import org.junit.jupiter.api.Test;
import sipeip.domain.entity.Objective;
import sipeip.service.mapper.ObjectiveMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveMapperTest {

    private final ObjectiveMapper objectiveMapper = new ObjectiveMapper();

    @Test
    void shouldMapObjectiveToObjectiveResponse() {
        Objective objective = Objective.builder()
                .id(1)
                .name("Objective Name")
                .code("Code123")
                .description("Description")
                .objectiveType("Type1")
                .eje("Eje1")
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ObjectiveResponse response = objectiveMapper.toObjectiveResponse(objective);

        assertNotNull(response);
        assertEquals(objective.getId(), response.getId());
        assertEquals(objective.getName(), response.getName());
        assertEquals(objective.getCode(), response.getCode());
        assertEquals(objective.getDescription(), response.getDescription());
        assertEquals(objective.getObjectiveType(), response.getType());
        assertEquals(objective.getEje(), response.getEje());
        assertEquals(objective.getStatus(), response.getStatus());
        assertEquals(String.valueOf(objective.getCreatedAt()), response.getCreatedAt());
        assertEquals(String.valueOf(objective.getUpdatedAt()), response.getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenMappingNullObjective() {
        ObjectiveResponse response = objectiveMapper.toObjectiveResponse(null);

        assertNull(response);
    }

    @Test
    void shouldMapObjectiveListToObjectiveResponseList() {
        Objective objective1 = Objective.builder()
                .id(1)
                .name("Objective 1")
                .code("Code1")
                .description("Description 1")
                .objectiveType("Type1")
                .eje("Eje1")
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Objective objective2 = Objective.builder()
                .id(2)
                .name("Objective 2")
                .code("Code2")
                .description("Description 2")
                .objectiveType("Type2")
                .eje("Eje2")
                .status("INACTIVE")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<ObjectiveResponse> responses = objectiveMapper.toObjectiveResponseFromObjective(List.of(objective1, objective2));

        assertNotNull(responses);
        assertEquals(2, responses.size());

        ObjectiveResponse response1 = responses.get(0);
        assertEquals(objective1.getId(), response1.getId());
        assertEquals(objective1.getName(), response1.getName());

        ObjectiveResponse response2 = responses.get(1);
        assertEquals(objective2.getId(), response2.getId());
        assertEquals(objective2.getName(), response2.getName());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyObjectiveList() {
        List<ObjectiveResponse> responses = objectiveMapper.toObjectiveResponseFromObjective(List.of());

        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }
}