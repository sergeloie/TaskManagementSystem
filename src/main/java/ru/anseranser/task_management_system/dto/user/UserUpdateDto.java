package ru.anseranser.task_management_system.dto.user;

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
public class UserUpdateDto {
    @Size(min = 3)
    String password;
}