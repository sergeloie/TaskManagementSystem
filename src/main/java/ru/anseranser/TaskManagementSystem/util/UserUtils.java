package ru.anseranser.TaskManagementSystem.util;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.model.Task;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.TaskRepository;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username).get();
    }

    public long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public boolean isUserTheOwner(Long id) {
        return id == getCurrentUserId();
    }

    public boolean isUserTheAuthor(Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);
        Long authorId = task.getAuthor().getId();
        return authorId == getCurrentUserId();
    }

    public boolean isUserTheExecutor(Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);
        Long executorId = task.getExecutor().getId();
        return executorId == getCurrentUserId();
    }
}
