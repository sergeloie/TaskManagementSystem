package ru.anseranser.TaskManagementSystem.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;

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

    public boolean isUserTheOwner(long id) {
        return id == getCurrentUserId();
    }
}
