package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for create Task")
public class TaskCreateDto {
    @NotNull
    @Schema(description = "Task header")
    String header;

    @Schema(description = "Task description")
    String description;

    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    @Schema(description = "Task Status", defaultValue = "CREATED")
    TaskStatus taskStatus = TaskStatus.CREATED;

    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    @Schema(description = "Task priority", defaultValue = "LOW")
    TaskPriority taskPriority = TaskPriority.LOW;

    @Schema(description = "Task Executor id")
    Long executorId;
}