package ru.anseranser.task_management_system.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.anseranser.task_management_system.dto.task.TaskChangeStatusDto;
import ru.anseranser.task_management_system.dto.task.TaskCreateDto;
import ru.anseranser.task_management_system.dto.task.TaskDto;
import ru.anseranser.task_management_system.dto.task.TaskParamsDto;
import ru.anseranser.task_management_system.dto.task.TaskUpdateDto;
import ru.anseranser.task_management_system.service.TaskService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @GetMapping
    public Page<TaskDto> getList(@ParameterObject TaskParamsDto taskParamsDto, @ParameterObject Pageable pageable) {
        return taskService.findAll(taskParamsDto, pageable);
    }

    @GetMapping("/{id}")
    public TaskDto getOne(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping("/by-ids")
    public List<TaskDto> getMany(@RequestParam List<Long> ids) {
        return taskService.findAllById(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody TaskCreateDto taskCreateDto) {
        return taskService.save(taskCreateDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheTaskAuthor(#id)")
    public TaskDto patch(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) throws IOException {
        return taskService.patch(id, taskUpdateDto);
    }

    @PatchMapping("/{id}/change-status")
    @PreAuthorize("@userUtils.isUserTheTaskExecutor(#id)")
    public TaskDto patchStatus(@PathVariable Long id, @RequestBody TaskChangeStatusDto taskChangeStatusDto) {
        return taskService.patchStatus(id, taskChangeStatusDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheTaskAuthor(#id)")
    public TaskDto delete(@PathVariable Long id) {
        return taskService.deleteById(id);
    }

    @GetMapping("/author/{id}")
    public Page<TaskDto> getAllByAuthorId(@PathVariable Long id, @ParameterObject Pageable pageable) {
        return taskService.findAllByAuthor_Id(id, pageable);
    }

    @GetMapping("/executor/{id}")
    public Page<TaskDto> getAllByExecutorId(@PathVariable Long id, @ParameterObject Pageable pageable) {
        return taskService.findAllByExecutor_Id(id, pageable);
    }
}
