package ru.anseranser.task_management_system.dto.user;

import lombok.Value;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.User}
 */
@Value
public class UserDto {
    Long id;
    String username;
}