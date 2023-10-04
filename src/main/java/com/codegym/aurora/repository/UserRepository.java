package com.codegym.aurora.repository;

import com.codegym.aurora.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findUserById(long id);

    User findUserByEmail(String email);
}
