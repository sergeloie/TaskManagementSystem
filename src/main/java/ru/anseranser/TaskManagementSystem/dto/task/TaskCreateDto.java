package ru.anseranser.TaskManagementSystem.dto.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.Task}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCreateDto {
    @NotNull
    String header;
    String description;
    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    TaskStatus taskStatus = TaskStatus.CREATED;
    @NotNull
    @JsonSetter(nulls = Nulls.SKIP)
    TaskPriority taskPriority = TaskPriority.LOW;
//    Long authorId;
    Long executorId;
}