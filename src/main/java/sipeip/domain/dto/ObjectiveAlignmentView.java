package sipeip.domain.dto;

import java.time.LocalDateTime;

public interface ObjectiveAlignmentView {
    Integer getAlignmentId();
    Integer getStrategicObjectiveId();
    String getStrategicObjectiveName();
    String getStrategicObjectiveType();
    Integer getPndObjectiveId();
    String getPndObjectiveName();
    Integer getOdsObjectiveId();
    String getOdsObjectiveName();
    Integer getEntityId();
    String getEntityName();
    String getStatus();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
