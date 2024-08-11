package ru.anseranser.TaskManagementSystem.utils;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.dto.user.UserCreateDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserUpdateDto;

@Getter
@Component
public class UserGenerator {
    private Model<UserCreateDto> userCreateDtoModel;
    private Model<UserUpdateDto> userUpdateDtoModel;

    @Autowired
    private Faker faker;

    @PostConstruct
    private void init() {

        userCreateDtoModel = Instancio.of(UserCreateDto.class)
                .supply(Select.field(UserCreateDto::getUsername), () -> faker.internet().emailAddress())
                .toModel();

        userUpdateDtoModel = Instancio.of(UserUpdateDto.class)
                .supply(Select.field(UserUpdateDto::getPassword), () -> faker.text().text(10, 20))
                .toModel();
    }

}
