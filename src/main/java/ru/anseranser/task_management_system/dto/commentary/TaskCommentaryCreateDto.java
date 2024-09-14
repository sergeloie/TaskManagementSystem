package ru.anseranser.task_management_system.dto.commentary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for create Task Commentary")
public class TaskCommentaryCreateDto {

    @Schema(description = "Task id")
    Long taskId;

    @NotNull
    @NotEmpty
    @Schema(description = "Commentary content")
    String commentBody;
}