package ru.anseranser.task_management_system.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.task_management_system.model.User}
 */
@Value
@Schema(description = "DTO for User")
public class UserDto {

    @Schema(description = "User id")
    Long id;

    @Schema(description = "User name")
    String username;
}