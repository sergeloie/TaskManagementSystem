package ru.anseranser.task_management_system.dto.user;

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
public class UserCreateDto {
    @NotNull
    @Email
    String username;
    @NotNull
    @Size(min = 3)
    String password;
}