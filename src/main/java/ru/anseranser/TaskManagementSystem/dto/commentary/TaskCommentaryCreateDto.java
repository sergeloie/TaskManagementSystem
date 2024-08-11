package ru.anseranser.TaskManagementSystem.dto.commentary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.TaskCommentary}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCommentaryCreateDto {
    Long taskId;
//    Long authorId;
    @NotNull
    @NotEmpty
    String commentBody;
}