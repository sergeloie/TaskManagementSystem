package ru.anseranser.task_management_system.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.User}
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(force = true)
@Schema(description = "DTO for update User")
public class UserUpdateDto {

    @Size(min = 3)
    @Schema(description = "New password")
    String password;
}