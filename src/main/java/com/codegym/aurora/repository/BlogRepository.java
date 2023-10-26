package com.codegym.aurora.repository;

import com.codegym.aurora.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByPublishIsTrue();
}
