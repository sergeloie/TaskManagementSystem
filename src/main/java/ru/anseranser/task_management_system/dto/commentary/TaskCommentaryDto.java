package ru.anseranser.task_management_system.dto.commentary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.Instant;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.TaskCommentary}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO for Task Commentary")
public class TaskCommentaryDto {

    @Schema(description = "Commentary id")
    Long id;

    @Schema(description = "Task id")
    Long taskId;

    @Schema(description = "Commentary author username")
    String authorUsername;

    @Schema(description = "Commentary content")
    String commentBody;

    @Schema(description = "Commentary creation date")
    Instant createdAt;
}