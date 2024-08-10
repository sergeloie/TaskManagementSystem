package ru.anseranser.TaskManagementSystem.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.Task}
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