package ru.anseranser.TaskManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.model.Task;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.TaskRepository;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class ReferenceMapper {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Named("userById")
    public User getUserByID(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Named("taskById")
    public Task getTaskByID(Long id) {
        return taskRepository.getReferenceById(id);
    }
}
