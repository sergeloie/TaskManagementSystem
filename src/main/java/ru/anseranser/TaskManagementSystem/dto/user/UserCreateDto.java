package ru.anseranser.TaskManagementSystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.User}
 */
@Getter
@Setter
public class UserCreateDto {
    @NotNull
    @Email
    String username;
    @NotNull
    @Size(min = 3)
    String password;
}