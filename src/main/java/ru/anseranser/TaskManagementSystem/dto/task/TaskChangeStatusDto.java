package ru.anseranser.TaskManagementSystem.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.Task}
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskChangeStatusDto(@NotNull TaskStatus taskStatus) {
}