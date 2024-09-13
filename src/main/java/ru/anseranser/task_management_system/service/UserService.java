package ru.anseranser.task_management_system.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import ru.anseranser.task_management_system.dto.user.UserCreateDto;
import ru.anseranser.task_management_system.dto.user.UserDto;
import ru.anseranser.task_management_system.dto.user.UserUpdateDto;
import ru.anseranser.task_management_system.mapper.UserMapper;
import ru.anseranser.task_management_system.model.User;
import ru.anseranser.task_management_system.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    public UserDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userMapper.toDto(userOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id))));
    }

    public List<UserDto> findAllById(Iterable<Long> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto save(UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    public UserDto patch(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id `%s` not found".formatted(id)));
        userMapper.partialUpdate(userUpdateDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.toDto(user);
    }
}
