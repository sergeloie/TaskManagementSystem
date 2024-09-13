package ru.anseranser.task_management_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.anseranser.task_management_system.dto.user.UserCreateDto;
import ru.anseranser.task_management_system.dto.user.UserDto;
import ru.anseranser.task_management_system.dto.user.UserUpdateDto;
import ru.anseranser.task_management_system.mapper.UserMapper;
import ru.anseranser.task_management_system.model.User;
import ru.anseranser.task_management_system.repository.UserRepository;
import ru.anseranser.task_management_system.service.UserService;
import ru.anseranser.task_management_system.util.UserUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtils userUtils;
    private final UserService userService;

    private final ObjectMapper objectMapper;

    @GetMapping
    public Page<UserDto> getList(@ParameterObject Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/by-ids")
    public List<UserDto> getMany(@RequestParam List<Long> ids) {
        return userService.findAllById(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheOwner(#id)")
    public UserDto patch(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) throws IOException {
        return userService.patch(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@userUtils.isUserTheOwner(#id)")
    public UserDto delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
