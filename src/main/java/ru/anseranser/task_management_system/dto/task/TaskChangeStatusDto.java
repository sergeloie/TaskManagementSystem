package ru.anseranser.task_management_system.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.anseranser.task_management_system.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.Task}
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for change Task Status")
public record TaskChangeStatusDto(
        @NotNull
        @Schema(description = "New Task Status")
        TaskStatus taskStatus
) {
}