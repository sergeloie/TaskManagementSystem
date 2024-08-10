package ru.anseranser.TaskManagementSystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.anseranser.TaskManagementSystem.dto.task.TaskCreateDto;
import ru.anseranser.TaskManagementSystem.dto.task.TaskDto;
import ru.anseranser.TaskManagementSystem.dto.task.TaskUpdateDto;
import ru.anseranser.TaskManagementSystem.model.Task;

@Mapper(
        uses = ReferenceMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskMapper {
    @Mapping(source = "executorId", target = "executor", qualifiedByName = "userById")
    @Mapping(source = "authorId", target = "author", qualifiedByName = "userById")
    Task toEntity(TaskCreateDto taskCreateDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "executor.username", target = "executorUsername")
    TaskDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "executorId", target = "executor", qualifiedByName = "userById")
    void partialUpdate(TaskUpdateDto taskUpdateDto, @MappingTarget Task task);
}