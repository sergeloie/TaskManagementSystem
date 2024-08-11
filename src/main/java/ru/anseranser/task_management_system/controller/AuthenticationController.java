package ru.anseranser.task_management_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anseranser.task_management_system.dto.AuthRequest;
import ru.anseranser.task_management_system.util.JwtUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String create(@RequestBody AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());
        authenticationManager.authenticate(authentication);
        return jwtUtils.generateToken(authRequest.getUsername());
    }
}

