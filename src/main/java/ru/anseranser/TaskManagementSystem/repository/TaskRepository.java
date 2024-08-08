package ru.anseranser.TaskManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anseranser.TaskManagementSystem.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}