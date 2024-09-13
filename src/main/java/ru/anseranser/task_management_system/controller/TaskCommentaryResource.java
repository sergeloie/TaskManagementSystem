package ru.anseranser.task_management_system.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryCreateDto;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryDto;
import ru.anseranser.task_management_system.model.TaskCommentary;
import ru.anseranser.task_management_system.service.TaskCommentaryService;

import java.util.List;

@RestController
@RequestMapping("/taskCommentaries")
@RequiredArgsConstructor
public class TaskCommentaryResource {

    private final TaskCommentaryService taskCommentaryService;

    @GetMapping
    public Page<TaskCommentaryDto> getList(@ParameterObject Pageable pageable) {
        return taskCommentaryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TaskCommentaryDto getOne(@PathVariable Long id) {
        return taskCommentaryService.findById(id);
    }

    @GetMapping("/by-task-id/{id}")
    public Page<TaskCommentaryDto> getCommentsByTaskId(@PathVariable Long id, @ParameterObject Pageable pageable) {
        return taskCommentaryService.findAllByTask_Id(id, pageable);
    }

    @GetMapping("/by-ids")
    public List<TaskCommentaryDto> getMany(@RequestParam List<Long> ids) {
        return taskCommentaryService.findAllById(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCommentaryDto create(@RequestBody TaskCommentaryCreateDto taskCommentaryCreateDto) {
        return taskCommentaryService.create(taskCommentaryCreateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheCommentaryAuthor(#id)")
    public TaskCommentary delete(@PathVariable Long id) {
        return taskCommentaryService.delete(id);
    }
}
