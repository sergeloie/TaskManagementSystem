package ru.anseranser.task_management_system.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */
@Getter
@Setter
@Schema(description = "Filter parameters for GET /tasks")
public class TaskParamsDto {

    @Schema(description = "Is Task header contains substring")
    String headerContains;

    @Schema(description = "Task Status is")
    TaskStatus taskStatus;

    @Schema(description = "Task Priority is")
    TaskPriority taskPriority;

    @Schema(description = "Task Author id")
    Long authorId;

    @Schema(description = "Task Executor id")
    Long executorId;
}