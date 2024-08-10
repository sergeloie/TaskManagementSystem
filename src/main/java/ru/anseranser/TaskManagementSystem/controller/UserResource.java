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
import ru.anseranser.TaskManagementSystem.dto.user.UserUpdateDto;
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
    public Page<UserDto> getList(@ParameterObject Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<UserDto> getMany(@RequestParam List<Long> ids) {
        return userRepository.findAllById(ids).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id)));

        userMapper.partialUpdate(userUpdateDto, user);

        return userMapper.toDto(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.toDto(user);
    }
}
