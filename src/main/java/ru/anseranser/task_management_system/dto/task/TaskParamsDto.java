package ru.anseranser.task_management_system.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.anseranser.task_management_system.enums.TaskPriority;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */
@Getter
@Setter
public class TaskParamsDto {
    String headerContains;
    TaskStatus taskStatus;
    TaskPriority taskPriority;
    Long authorId;
    Long executorId;
}