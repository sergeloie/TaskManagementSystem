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
public class TaskDto {
    Long id;
    String header;
    String description;
    TaskStatus taskStatus;
    TaskPriority taskPriority;
    String authorUsername;
    String executorUsername;
}