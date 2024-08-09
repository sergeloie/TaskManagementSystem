package ru.anseranser.TaskManagementSystem.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskUpdateDto {
    String header;
    String description;
    TaskStatus taskStatus;
    TaskPriority taskPriority;
    Long executorId;
}