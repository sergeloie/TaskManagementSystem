package ru.anseranser.task_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryCreateDto;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryDto;
import ru.anseranser.task_management_system.mapper.TaskCommentaryMapper;
import ru.anseranser.task_management_system.model.TaskCommentary;
import ru.anseranser.task_management_system.repository.TaskCommentaryRepository;
import ru.anseranser.task_management_system.util.UserUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskCommentaryService {

    private final TaskCommentaryRepository taskCommentaryRepository;
    private final TaskCommentaryMapper taskCommentaryMapper;
    private final UserUtils userUtils;

    public Page<TaskCommentaryDto> findAll(Pageable pageable) {
        Page<TaskCommentary> taskCommentaries = taskCommentaryRepository.findAll(pageable);
        return taskCommentaries.map(taskCommentaryMapper::toDto);
    }

    public TaskCommentaryDto findById(Long id) {
        Optional<TaskCommentary> taskCommentaryOptional = taskCommentaryRepository.findById(id);
        return taskCommentaryMapper.toDto(taskCommentaryOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentary with id `%s` not found".formatted(id))));
    }

    public Page<TaskCommentaryDto> findAllByTask_Id(Long id, Pageable pageable) {
        Page<TaskCommentary> taskCommentaries = taskCommentaryRepository.findAllByTask_Id(id, pageable);
        return taskCommentaries.map(taskCommentaryMapper::toDto);
    }

    public List<TaskCommentaryDto> findAllById(Iterable<Long> ids) {
        List<TaskCommentary> taskCommentaries = taskCommentaryRepository.findAllById(ids);
        return taskCommentaries.stream()
                .map(taskCommentaryMapper::toDto)
                .toList();
    }

    public TaskCommentaryDto create(TaskCommentaryCreateDto taskCommentaryCreateDto) {
        TaskCommentary taskCommentary = taskCommentaryMapper.toEntity(taskCommentaryCreateDto);
        taskCommentary.setAuthor(userUtils.getCurrentUser());
        taskCommentaryRepository.save(taskCommentary);
        return taskCommentaryMapper.toDto(taskCommentary);
    }

    public TaskCommentary delete(Long id) {
        TaskCommentary taskCommentary = taskCommentaryRepository.findById(id).orElse(null);
        if (taskCommentary != null) {
            taskCommentaryRepository.delete(taskCommentary);
        }
        return taskCommentary;
    }
}
