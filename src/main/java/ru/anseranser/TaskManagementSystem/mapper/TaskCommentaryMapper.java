package ru.anseranser.TaskManagementSystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.anseranser.TaskManagementSystem.dto.commentary.TaskCommentaryCreateDto;
import ru.anseranser.TaskManagementSystem.dto.commentary.TaskCommentaryDto;
import ru.anseranser.TaskManagementSystem.model.Task;
import ru.anseranser.TaskManagementSystem.model.TaskCommentary;
import ru.anseranser.TaskManagementSystem.model.User;

@Mapper(
        uses = ReferenceMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskCommentaryMapper {

    @Mapping(source = "authorId", target = "author", qualifiedByName = "userById")
    @Mapping(source = "taskId", target = "task", qualifiedByName = "taskById")
    TaskCommentary toEntity(TaskCommentaryCreateDto taskCommentaryCreateDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "task.id", target = "taskId")
    TaskCommentaryDto toDto(TaskCommentary taskCommentary);

}