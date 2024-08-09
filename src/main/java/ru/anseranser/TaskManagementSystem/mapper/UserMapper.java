package ru.anseranser.TaskManagementSystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.anseranser.TaskManagementSystem.dto.user.UserCreateDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserUpdateDto;
import ru.anseranser.TaskManagementSystem.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UserMapper {
    User toEntity(UserCreateDto userCreateDto);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget User user);

}