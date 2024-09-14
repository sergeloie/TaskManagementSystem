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
@Schema(description = "DTO for update Task")
public class TaskUpdateDto {

    @Schema(description = "New Task header")
    String header;

    @Schema(description = "New Task description")
    String description;

    @Schema(description = "New Task Status")
    TaskStatus taskStatus;

    @Schema(description = "New Task Priority")
    TaskPriority taskPriority;

    @Schema(description = "New Task Executor id")
    Long executorId;
}