package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto {
    @NotNull
    String header;
    String description;
    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    TaskStatus taskStatus = TaskStatus.CREATED;
    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    TaskPriority taskPriority = TaskPriority.LOW;
//    Long authorId;
    Long executorId;
}