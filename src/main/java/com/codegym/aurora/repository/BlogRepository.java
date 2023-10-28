package com.codegym.aurora.repository;

import com.codegym.aurora.entity.Blog;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByPublishIsTrue();

    @Query(nativeQuery = true,
            value = "SELECT * from blog where publish = true " +
                    "and (title like concat('%',:keyword,'%') " +
                    "or author like concat('%',:keyword,'%') " +
                    "or description like concat('%',:keyword,'%'))")
    List<Blog> searchBlogs(@Param("keyword") String keyword);
}
