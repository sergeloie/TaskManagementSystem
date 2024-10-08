package ru.anseranser.task_management_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.anseranser.task_management_system.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Page<Task> findAllByAuthor_Id(Long id, Pageable pageable);
    Page<Task> findAllByExecutor_Id(Long id, Pageable pageable);
}