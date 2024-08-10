package ru.anseranser.TaskManagementSystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    void partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget User user);

    @BeforeMapping
    default void encryptPassword(UserCreateDto userCreateDto, PasswordEncoder encoder) {
        String password = userCreateDto.getPassword();
        userCreateDto.setPassword(encoder.encode(password));
    }

    @BeforeMapping
    default void encryptPassword(UserUpdateDto userUpdateDto, PasswordEncoder encoder) {
        String password = userUpdateDto.getPassword();
        if (password != null) {
            userUpdateDto.setPassword(encoder.encode(password));
        }
    }

}