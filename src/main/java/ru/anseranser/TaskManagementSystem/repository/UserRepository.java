package ru.anseranser.TaskManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anseranser.TaskManagementSystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}