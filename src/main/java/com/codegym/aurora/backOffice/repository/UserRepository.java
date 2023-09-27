package com.codegym.aurora.backOffice.repository;

import com.codegym.aurora.backOffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
