package ru.anseranser.task_management_system.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryCreateDto;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryDto;
import ru.anseranser.task_management_system.model.TaskCommentary;

@Mapper(
        uses = {ReferenceMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskCommentaryMapper {

//    @Mapping(source = "authorId", target = "author", qualifiedByName = "userById")
    @Mapping(source = "taskId", target = "task", qualifiedByName = "taskById")
    TaskCommentary toEntity(TaskCommentaryCreateDto taskCommentaryCreateDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "task.id", target = "taskId")
    TaskCommentaryDto toDto(TaskCommentary taskCommentary);

}