package ru.anseranser.TaskManagementSystem.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.anseranser.TaskManagementSystem.dto.task.TaskParamsDto;
import ru.anseranser.TaskManagementSystem.model.Task;


public record TaskFilter(TaskParamsDto taskParamsDto) {
    public Specification<Task> toSpecification() {
        return Specification.where(headerContainsSpec())
                .and(taskStatusSpec())
                .and(taskPrioritySpec())
                .and(authorIdSpec())
                .and(executorIdSpec());
    }

    private Specification<Task> headerContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(taskParamsDto.getHeaderContains())
                ? cb.like(cb.lower(root.get("header")), "%" + taskParamsDto.getHeaderContains().toLowerCase() + "%")
                : null);
    }

    private Specification<Task> taskStatusSpec() {
        return ((root, query, cb) -> taskParamsDto.getTaskStatus() != null
                ? cb.equal(root.get("taskStatus"), taskParamsDto.getTaskStatus())
                : null);
    }

    private Specification<Task> taskPrioritySpec() {
        return ((root, query, cb) -> taskParamsDto.getTaskPriority() != null
                ? cb.equal(root.get("taskPriority"), taskParamsDto.getTaskPriority())
                : null);
    }

    private Specification<Task> authorIdSpec() {
        return ((root, query, cb) -> taskParamsDto.getAuthorId() != null
                ? cb.equal(root.get("author").get("id"), taskParamsDto.getAuthorId())
                : null);
    }

    private Specification<Task> executorIdSpec() {
        return ((root, query, cb) -> taskParamsDto.getExecutorId() != null
                ? cb.equal(root.get("executor").get("id"), taskParamsDto.getExecutorId())
                : null);
    }
}