package com.codegym.aurora.repository;

import com.codegym.aurora.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
