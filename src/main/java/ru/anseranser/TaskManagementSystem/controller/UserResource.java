package ru.anseranser.TaskManagementSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.TaskManagementSystem.dto.user.UserCreateDto;
import ru.anseranser.TaskManagementSystem.dto.user.UserDto;
import ru.anseranser.TaskManagementSystem.mapper.UserMapper;
import ru.anseranser.TaskManagementSystem.model.User;
import ru.anseranser.TaskManagementSystem.repository.UserRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final ObjectMapper objectMapper;

    @GetMapping
    public Page<User> getList(@ParameterObject Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<User> getMany(@RequestParam List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @PatchMapping("/{id}")
    public User patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(user).readValue(patchNode);

        return userRepository.save(user);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam @Valid List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<User> users = userRepository.findAllById(ids);

        for (User user : users) {
            objectMapper.readerForUpdating(user).readValue(patchNode);
        }

        List<User> resultUsers = userRepository.saveAll(users);
        return resultUsers.stream()
                .map(User::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
