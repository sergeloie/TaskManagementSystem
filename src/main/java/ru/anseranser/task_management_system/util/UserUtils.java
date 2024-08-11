package ru.anseranser.task_management_system.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.anseranser.task_management_system.model.Task;
import ru.anseranser.task_management_system.model.TaskCommentary;
import ru.anseranser.task_management_system.model.User;
import ru.anseranser.task_management_system.repository.TaskCommentaryRepository;
import ru.anseranser.task_management_system.repository.TaskRepository;
import ru.anseranser.task_management_system.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskCommentaryRepository taskCommentaryRepository;


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

    public boolean isUserTheTaskAuthor(Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);
        Long authorId = task.getAuthor().getId();
        return authorId == getCurrentUserId();
    }

    public boolean isUserTheTaskExecutor(Long taskId) {
        Task task = taskRepository.getReferenceById(taskId);
        Long executorId = task.getExecutor().getId();
        return executorId == getCurrentUserId();
    }

    public boolean isUserTheCommentaryAuthor(Long commentId) {
        TaskCommentary taskCommentary = taskCommentaryRepository.getReferenceById(commentId);
        Long authorId = taskCommentary.getAuthor().getId();
        return authorId == getCurrentUserId();
    }
}
