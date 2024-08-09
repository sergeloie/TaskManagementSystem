package ru.anseranser.TaskManagementSystem.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto {
    @NotNull
    String header;
    String description;
    @NotNull
    TaskStatus taskStatus;
    @NotNull
    TaskPriority taskPriority;
    Long authorId;
    Long executorId;
}