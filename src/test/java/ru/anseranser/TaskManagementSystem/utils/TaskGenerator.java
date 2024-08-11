package ru.anseranser.TaskManagementSystem.utils;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.anseranser.TaskManagementSystem.dto.task.TaskCreateDto;
import ru.anseranser.TaskManagementSystem.dto.task.TaskUpdateDto;
import ru.anseranser.TaskManagementSystem.enums.TaskPriority;
import ru.anseranser.TaskManagementSystem.enums.TaskStatus;

@Getter
@Component
public class TaskGenerator {
    private Model<TaskCreateDto> taskCreateDtoModel;
    private Model<TaskUpdateDto> taskUpdateDtoModel;

    @Autowired
    private Faker faker;

    @PostConstruct
    private void init() {
        taskCreateDtoModel = Instancio.of(TaskCreateDto.class)
                .supply(Select.field(TaskCreateDto::getHeader), () -> faker.artist().name())
                .supply(Select.field(TaskCreateDto::getDescription), () -> faker.lorem().sentence())
                .supply(Select.field(TaskCreateDto::getTaskStatus), () -> faker.options().option(TaskStatus.class))
                .supply(Select.field(TaskCreateDto::getTaskPriority), () -> faker.options().option(TaskPriority.class))
                .supply(Select.field(TaskCreateDto::getExecutorId), () -> 1L)
                .toModel();

        taskUpdateDtoModel = Instancio.of(TaskUpdateDto.class)
                .supply(Select.field(TaskUpdateDto::getHeader), () -> faker.artist().name())
                .supply(Select.field(TaskUpdateDto::getDescription), () -> faker.lorem().sentence())
                .supply(Select.field(TaskUpdateDto::getTaskStatus), () -> faker.options().option(TaskStatus.class))
                .supply(Select.field(TaskUpdateDto::getTaskPriority), () -> faker.options().option(TaskPriority.class))
                .supply(Select.field(TaskUpdateDto::getExecutorId), () -> 1L)
                .toModel();
    }
}
