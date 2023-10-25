package com.codegym.aurora.repository;

import com.codegym.aurora.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Should return Optional<User>

    User findUserById(long id);

    User findByUserDetailEmail(String email);

}
