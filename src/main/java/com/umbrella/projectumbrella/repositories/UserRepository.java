package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.dto.Role;
import com.umbrella.projectumbrella.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
