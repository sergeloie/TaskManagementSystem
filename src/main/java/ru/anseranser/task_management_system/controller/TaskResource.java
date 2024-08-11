package ru.anseranser.task_management_system.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.task_management_system.dto.task.TaskChangeStatusDto;
import ru.anseranser.task_management_system.dto.task.TaskCreateDto;
import ru.anseranser.task_management_system.dto.task.TaskDto;
import ru.anseranser.task_management_system.dto.task.TaskParamsDto;
import ru.anseranser.task_management_system.dto.task.TaskUpdateDto;
import ru.anseranser.task_management_system.mapper.TaskMapper;
import ru.anseranser.task_management_system.model.Task;
import ru.anseranser.task_management_system.repository.TaskRepository;
import ru.anseranser.task_management_system.specification.TaskFilter;
import ru.anseranser.task_management_system.util.UserUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserUtils userUtils;

    @GetMapping
    public Page<TaskDto> getList(@ParameterObject TaskParamsDto taskParamsDto, @ParameterObject Pageable pageable) {
        Specification<Task> spec = new TaskFilter(taskParamsDto).toSpecification();
        Page<Task> tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(taskMapper::toDto);
    }

    @GetMapping("/{id}")
    public TaskDto getOne(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskMapper.toDto(taskOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<TaskDto> getMany(@RequestParam List<Long> ids) {

        return taskRepository.findAllById(ids).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody TaskCreateDto taskCreateDto) {
        Task task = taskMapper.toEntity(taskCreateDto);
        task.setAuthor(userUtils.getCurrentUser());
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheTaskAuthor(#id)")
    public TaskDto patch(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id)));

        taskMapper.partialUpdate(taskUpdateDto, task);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @PatchMapping("/{id}/change-status")
    @PreAuthorize("@userUtils.isUserTheTaskExecutor(#id)")
    public TaskDto patchStatus(@PathVariable Long id, @RequestBody TaskChangeStatusDto taskChangeStatusDto) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id `%s` not found".formatted(id)));

        taskMapper.partialUpdate(taskChangeStatusDto, task);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheTaskAuthor(#id)")
    public TaskDto delete(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        }
        return taskMapper.toDto(task);
    }

    @GetMapping("/author/{id}")
    public Page<TaskDto> getAllByAuthorId(@PathVariable Long id, @ParameterObject Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByAuthor_Id(id, pageable);
        return tasks.map(taskMapper::toDto);
    }

    @GetMapping("/executor/{id}")
    public Page<TaskDto> getAllByExecutorId(@PathVariable Long id, @ParameterObject Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByExecutor_Id(id, pageable);
        return tasks.map(taskMapper::toDto);
    }
}
