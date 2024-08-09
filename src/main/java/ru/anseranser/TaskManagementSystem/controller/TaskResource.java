package ru.anseranser.TaskManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.TaskManagementSystem.dto.task.TaskCreateDto;
import ru.anseranser.TaskManagementSystem.dto.task.TaskDto;
import ru.anseranser.TaskManagementSystem.mapper.TaskMapper;
import ru.anseranser.TaskManagementSystem.model.Task;
import ru.anseranser.TaskManagementSystem.repository.TaskRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public Page<TaskDto> getList(@ParameterObject Pageable pageable) {

        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(taskMapper::toDto);
    }

    @GetMapping("/{id}")
    public TaskDto getOne(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskMapper.toDto(taskOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<TaskDto> getMany(@RequestParam List<Long> ids) {

        return taskRepository.findAllById(ids).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto create(@RequestBody TaskCreateDto taskCreateDto) {
        Task task = taskMapper.toEntity(taskCreateDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @PatchMapping("/{id}")
    public TaskDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(task).readValue(patchNode);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        }
        return taskMapper.toDto(task);
    }
}
