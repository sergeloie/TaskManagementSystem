package ru.anseranser.task_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.task_management_system.dto.task.TaskChangeStatusDto;
import ru.anseranser.task_management_system.dto.task.TaskCreateDto;
import ru.anseranser.task_management_system.dto.task.TaskDto;
import ru.anseranser.task_management_system.dto.task.TaskParamsDto;
import ru.anseranser.task_management_system.dto.task.TaskUpdateDto;
import ru.anseranser.task_management_system.mapper.TaskMapper;
import ru.anseranser.task_management_system.model.Task;
import ru.anseranser.task_management_system.repository.TaskCommentaryRepository;
import ru.anseranser.task_management_system.repository.TaskRepository;
import ru.anseranser.task_management_system.specification.TaskFilter;
import ru.anseranser.task_management_system.util.UserUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserUtils userUtils;

    private final TaskCommentaryRepository taskCommentaryRepository;

    public Page<TaskDto> findAll(TaskParamsDto taskParamsDto, Pageable pageable) {
        Specification<Task> spec = new TaskFilter(taskParamsDto).toSpecification();
        Page<Task> tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(taskMapper::toDto);
    }

    public TaskDto findById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskMapper.toDto(taskOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id))));
    }

    public List<TaskDto> findAllById(Iterable<Long> ids) {
        List<Task> tasks = taskRepository.findAllById(ids);
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public TaskDto save(TaskCreateDto taskCreateDto) {
        Task task = taskMapper.toEntity(taskCreateDto);
        task.setAuthor(userUtils.getCurrentUser());
        Task resultTask = taskRepository.save(task);
        return taskMapper.toDto(resultTask);
    }

    public TaskDto patch(Long id,  TaskUpdateDto taskUpdateDto) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id)));
        taskMapper.partialUpdate(taskUpdateDto, task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto patchStatus(Long id, TaskChangeStatusDto taskChangeStatusDto) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id)));
        taskMapper.partialUpdate(taskChangeStatusDto, task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto deleteById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        }
        return taskMapper.toDto(task);
    }

    public Page<TaskDto> findAllByAuthor_Id(Long id, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByAuthor_Id(id, pageable);
        return tasks.map(taskMapper::toDto);
    }

    public Page<TaskDto> findAllByExecutor_Id(Long id, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByExecutor_Id(id, pageable);
        return tasks.map(taskMapper::toDto);
    }
}

