package ru.anseranser.task_management_system.utils;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.anseranser.task_management_system.dto.commentary.TaskCommentaryCreateDto;

@Getter
@Component
public class CommentGenerator {
    private Model<TaskCommentaryCreateDto> commentaryCreateDtoModel;

    @Autowired
    private Faker faker;

    @PostConstruct
    private void inti() {
        commentaryCreateDtoModel = Instancio.of(TaskCommentaryCreateDto.class)
                .ignore(Select.field(TaskCommentaryCreateDto::getTaskId))
                .supply(Select.field(TaskCommentaryCreateDto::getCommentBody), () -> faker.lorem().sentence())
                .toModel();
    }
}
