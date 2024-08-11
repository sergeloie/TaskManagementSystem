package ru.anseranser.TaskManagementSystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.anseranser.TaskManagementSystem.dto.user.UserCreateDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserUpdateDto;
import ru.anseranser.TaskManagementSystem.model.User;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)

public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract User toEntity(UserCreateDto userCreateDto);

    public abstract UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget User user);

    @BeforeMapping
    public void encryptPassword(UserCreateDto userCreateDto) {
        String password = userCreateDto.getPassword();
        userCreateDto.setPassword(passwordEncoder.encode(password));
    }

    @BeforeMapping
    public void encryptPassword(UserUpdateDto userUpdateDto) {
        String password = userUpdateDto.getPassword();
        if (password != null) {
            userUpdateDto.setPassword(passwordEncoder.encode(password));
        }
    }
}
