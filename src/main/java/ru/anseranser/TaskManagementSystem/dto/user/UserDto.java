package ru.anseranser.TaskManagementSystem.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.User}
 */
@Value
public class UserDto {
    Long id;
    String username;
}