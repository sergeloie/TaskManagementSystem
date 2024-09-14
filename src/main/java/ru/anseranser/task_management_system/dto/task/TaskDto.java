package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Task DTO")
public class TaskDto {
    @Schema(description = "Task id")
    Long id;

    @Schema(description = "Task header")
    String header;

    @Schema(description = "Task description")
    String description;

    @Schema(description = "Task status")
    TaskStatus taskStatus;

    @Schema(description = "Task priority")
    TaskPriority taskPriority;

    @Schema(description = "Task Author username")
    String authorUsername;

    @Schema(description = "Task Executor username")
    String executorUsername;
}