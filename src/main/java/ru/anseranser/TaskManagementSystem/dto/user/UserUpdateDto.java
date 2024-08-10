package ru.anseranser.TaskManagementSystem.dto.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

/**
 * DTO for {@link ru.anseranser.TaskManagementSystem.model.User}
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(force = true)
public class UserUpdateDto {
    @Size(min = 3)
    String password;
}