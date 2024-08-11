package ru.anseranser.TaskManagementSystem.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;
import ru.anseranser.TaskManagementSystem.model.Task;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.TaskRepository;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder encoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String username = "admin@admin.ru";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);

        Task task = new Task();
        task.setAuthor(user);
        task.setExecutor(user);
        task.setHeader("First task");
        task.setDescription("Description");
        task.setTaskStatus(TaskStatus.CREATED);
        task.setTaskPriority(TaskPriority.MEDIUM);
        taskRepository.save(task);

        System.out.println(task);
    }
}
