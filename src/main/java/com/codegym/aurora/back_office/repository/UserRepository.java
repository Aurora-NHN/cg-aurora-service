package com.codegym.aurora.back_office.repository;

import com.codegym.aurora.back_office.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findUserById(long id);
}
