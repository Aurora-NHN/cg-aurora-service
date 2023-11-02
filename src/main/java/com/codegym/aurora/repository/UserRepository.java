package com.codegym.aurora.repository;

import com.codegym.aurora.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Should return Optional<User>

    User findUserById(long id);

    User findByUserDetailEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isDelete = false AND u.username LIKE %:username%")
    Page<User> findAllUser(Pageable pageable,@Param("username") String username);

}
