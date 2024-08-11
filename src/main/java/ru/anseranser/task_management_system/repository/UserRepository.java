package ru.anseranser.task_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anseranser.task_management_system.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}