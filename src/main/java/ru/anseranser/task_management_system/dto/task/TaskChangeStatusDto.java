package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskChangeStatusDto(@NotNull TaskStatus taskStatus) {
}