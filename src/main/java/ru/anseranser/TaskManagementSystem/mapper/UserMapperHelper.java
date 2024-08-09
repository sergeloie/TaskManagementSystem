package ru.anseranser.TaskManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserMapperHelper {
    private final UserRepository userRepository;

    @Named("userById")
    public User map(Long id) {
        return userRepository.getReferenceById(id);
    }
}
