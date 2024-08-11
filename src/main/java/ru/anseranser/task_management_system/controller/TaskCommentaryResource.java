package ru.anseranser.task_management_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryCreateDto;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryDto;
import ru.anseranser.task_management_system.mapper.TaskCommentaryMapper;
import ru.anseranser.task_management_system.model.TaskCommentary;
import ru.anseranser.task_management_system.repository.TaskCommentaryRepository;
import ru.anseranser.task_management_system.util.UserUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskCommentaries")
@RequiredArgsConstructor
public class TaskCommentaryResource {

    private final TaskCommentaryRepository taskCommentaryRepository;
    private final UserUtils userUtils;

    private final ObjectMapper objectMapper;
    private final TaskCommentaryMapper taskCommentaryMapper;

    @GetMapping
    public Page<TaskCommentaryDto> getList(@ParameterObject Pageable pageable) {
        Page<TaskCommentary> commentaries = taskCommentaryRepository.findAll(pageable);
        return commentaries.map(taskCommentaryMapper::toDto);
    }

    @GetMapping("/{id}")
    public TaskCommentaryDto getOne(@PathVariable Long id) {
        Optional<TaskCommentary> taskCommentaryOptional = taskCommentaryRepository.findById(id);
        return taskCommentaryMapper.toDto(taskCommentaryOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentary with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<TaskCommentaryDto> getMany(@RequestParam List<Long> ids) {
        return taskCommentaryRepository.findAllById(ids).stream()
                .map(taskCommentaryMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskCommentaryDto create(@RequestBody TaskCommentaryCreateDto taskCommentaryCreateDto) {
        TaskCommentary taskCommentary = taskCommentaryMapper.toEntity(taskCommentaryCreateDto);
        taskCommentary.setAuthor(userUtils.getCurrentUser());
        taskCommentaryRepository.save(taskCommentary);
        return taskCommentaryMapper.toDto(taskCommentary);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheCommentaryAuthor(#id)")
    public TaskCommentary delete(@PathVariable Long id) {
        TaskCommentary taskCommentary = taskCommentaryRepository.findById(id).orElse(null);
        if (taskCommentary != null) {
            taskCommentaryRepository.delete(taskCommentary);
        }
        return taskCommentary;
    }
}
