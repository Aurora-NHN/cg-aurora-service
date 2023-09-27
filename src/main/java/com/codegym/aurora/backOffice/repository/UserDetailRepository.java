package com.codegym.aurora.backOffice.repository;

import com.codegym.aurora.backOffice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailRepository extends JpaRepository<UserDetail, UUID> {
}
