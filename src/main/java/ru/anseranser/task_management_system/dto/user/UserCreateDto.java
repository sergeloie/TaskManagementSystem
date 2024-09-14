package ru.anseranser.task_management_system.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO for {@link ru.anseranser.task_management_system.model.User}
 */
@Getter
@Setter
@Schema(description = "User creation DTO")
public class UserCreateDto {
    @NotNull
    @Email
    @Schema(description = "Username, email format", example = "user@example.com")
    String username;

    @NotNull
    @Size(min = 3)
    @Schema(description = "User password, at least 3 characters")
    String password;
}