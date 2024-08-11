package ru.anseranser.task_management_system.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.anseranser.task_management_system.model.Task;
import ru.anseranser.task_management_system.model.User;
import ru.anseranser.task_management_system.repository.TaskRepository;
import ru.anseranser.task_management_system.repository.UserRepository;

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
