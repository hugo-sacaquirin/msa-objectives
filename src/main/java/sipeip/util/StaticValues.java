package sipeip.util;

import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;

public class StaticValues {
    private StaticValues() {
        // Prevent instantiation
    }

    public static final String CREATED = "201";
    public static ObjectiveResultResponse getObjectiveResultResponse(String message) {
        ObjectiveResultResponse entityResultResponse = new ObjectiveResultResponse();
        entityResultResponse.setCode(CREATED);
        entityResultResponse.setResult(message);
        return entityResultResponse;
    }
}
