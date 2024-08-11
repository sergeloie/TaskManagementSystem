package ru.anseranser.TaskManagementSystem.dto.user;

import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.User}
 */
@Value
public class UserDto {
    Long id;
    String username;
}