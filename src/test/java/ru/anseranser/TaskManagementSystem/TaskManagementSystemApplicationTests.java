package ru.anseranser.TaskManagementSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.anseranser.TaskManagementSystem.dto.task.TaskCreateDto;
import ru.anseranser.TaskManagementSystem.dto.task.TaskUpdateDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserCreateDto;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.utils.JsonFieldExtractor;
import ru.anseranser.TaskManagementSystem.utils.RequestSender;
import ru.anseranser.TaskManagementSystem.utils.TaskGenerator;
import ru.anseranser.TaskManagementSystem.utils.UserGenerator;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ComponentScan(basePackages = "ru.anseranser")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class TaskManagementSystemApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtRequestPostProcessor token;
    private User testUser;
    @Autowired
    private UserGenerator userGenerator;
    @Autowired
    private RequestSender requestSender;
    @Autowired
    private JsonFieldExtractor jsonFieldExtractor;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskGenerator taskGenerator;


    @Test
    void contextLoads() {
    }

    @Test
    void createUser() throws Exception {
        UserCreateDto userCreateDto = Instancio.of(userGenerator.getUserCreateDtoModel()).create();
        String result = requestSender.sendPostRequest("/users", userCreateDto)
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        assertThatJson(result).and(
                v -> v.node("id").isPresent(),
                v -> v.node("username").isEqualTo(userCreateDto.getUsername())
        );

        String testToken = requestSender.sendPostRequest("/login", userCreateDto)
                .andReturn().getResponse().getContentAsString();

        Long id = jsonFieldExtractor.getFieldAsLong(result, "id");

        result = requestSender.sendGetRequest("/users/" + id, testToken)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThatJson(result).and(
                v -> v.node("username").isEqualTo(userCreateDto.getUsername()));
    }

    @Test
    void createTask() throws Exception {
        UserCreateDto userCreateDto = Instancio.of(userGenerator.getUserCreateDtoModel()).create();
        String result = requestSender.sendPostRequest("/users", userCreateDto)
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String testToken = requestSender.sendPostRequest("/login", userCreateDto)
                .andReturn().getResponse().getContentAsString();

        TaskCreateDto taskCreateDto = Instancio.of(taskGenerator.getTaskCreateDtoModel()).create();

        result = requestSender.sendPostRequest("/tasks", taskCreateDto, testToken)
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long taskId = jsonFieldExtractor.getFieldAsLong(result, "id");

        assertThatJson(result).and(
                v -> v.node("id").isPresent(),
                v -> v.node("header").isEqualTo(taskCreateDto.getHeader()),
                v -> v.node("description").isEqualTo(taskCreateDto.getDescription()),
                v -> v.node("taskStatus").isEqualTo(taskCreateDto.getTaskStatus()),
                v -> v.node("taskPriority").isEqualTo(taskCreateDto.getTaskPriority()),
                v -> v.node("authorUsername").isEqualTo(userCreateDto.getUsername()));

        TaskUpdateDto taskUpdateDto = Instancio.of(taskGenerator.getTaskUpdateDtoModel()).create();

        result = requestSender.sendPatchRequest("/tasks/" + taskId, taskUpdateDto, testToken)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).and(
                v -> v.node("id").isPresent(),
                v -> v.node("header").isEqualTo(taskUpdateDto.getHeader()),
                v -> v.node("description").isEqualTo(taskUpdateDto.getDescription()),
                v -> v.node("taskStatus").isEqualTo(taskUpdateDto.getTaskStatus()),
                v -> v.node("taskPriority").isEqualTo(taskUpdateDto.getTaskPriority()));
    }


}
