package ru.anseranser.task_management_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.anseranser.task_management_system.model.TaskCommentary;

public interface TaskCommentaryRepository extends JpaRepository<TaskCommentary, Long> {
    Page<TaskCommentary> findAllByTask_Id(Long id, Pageable pageable);
}