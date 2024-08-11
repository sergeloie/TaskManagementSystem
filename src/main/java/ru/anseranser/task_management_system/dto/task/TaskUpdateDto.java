package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
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