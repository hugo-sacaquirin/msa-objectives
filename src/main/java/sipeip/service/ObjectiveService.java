package sipeip.service;


import com.sipeip.infrastructure.input.adapter.rest.models.ObjectivePagedResponse;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveRequest;
import com.sipeip.infrastructure.input.adapter.rest.models.ObjectiveResultResponse;

public interface ObjectiveService {
    ObjectiveResultResponse createObjective(ObjectiveRequest objective);

    ObjectiveResultResponse updateObjective(Integer id, ObjectiveRequest objective);

    void deactivateObjective(Integer id);

    ObjectivePagedResponse searchObjectives(Integer page, Integer size, String name, String typeObjective, String code, String type);

    ObjectivePagedResponse getPagedObjectives(Integer page, Integer size, String name, String typeObjective, String code);


}
