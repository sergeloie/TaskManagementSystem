package ru.anseranser.task_management_system.dto.commentary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.TaskCommentary}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCommentaryCreateDto {
    Long taskId;
    @NotNull
    @NotEmpty
    String commentBody;
}