package ru.anseranser.TaskManagementSystem.dto.commentary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.time.Instant;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.TaskCommentary}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCommentaryDto {
    Long id;
    Long taskId;
    String authorUsername;
    String commentBody;
    Instant createdAt;
}