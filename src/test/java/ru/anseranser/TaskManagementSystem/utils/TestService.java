package ru.anseranser.TaskManagementSystem.utils;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@Service
public class TestService {
    @Bean
    public Faker getFaker() {
        return new Faker();
    }

}
