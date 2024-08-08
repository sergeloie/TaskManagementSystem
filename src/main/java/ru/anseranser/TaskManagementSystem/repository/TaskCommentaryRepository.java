package ru.anseranser.TaskManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anseranser.TaskManagementSystem.model.TaskCommentary;

public interface TaskCommentaryRepository extends JpaRepository<TaskCommentary, Long> {
}