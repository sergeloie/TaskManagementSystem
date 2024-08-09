package ru.anseranser.TaskManagementSystem.dto.user;

import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.User}
 */
@Value
@NoArgsConstructor(force = true)
public class UserUpdateDto {
    @Size(min = 3)
    String password;
}