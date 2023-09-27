package com.codegym.aurora.back_office.repository;

import com.codegym.aurora.back_office.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
}
