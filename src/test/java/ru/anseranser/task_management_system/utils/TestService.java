package ru.anseranser.task_management_system.utils;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestService {
    @Bean
    public Faker getFaker() {
        return new Faker();
    }

}
