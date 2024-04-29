package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.dto.Role;
import com.umbrella.projectumbrella.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1 ")
    void updatePassword(String email, String password);

}
