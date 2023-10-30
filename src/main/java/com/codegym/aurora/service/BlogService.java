package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.BlogContentImageDto;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import com.codegym.aurora.payload.response.BlogCategoryResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {

    ResponseEntity<Object> save(BlogCreateRequestDto blogCreateRequestDto);
    ResponseEntity<Object> save(BlogUpdateRequestDto blogUpdateRequestDto);

    ResponseEntity<Object> uploadContentImage(BlogContentImageDto blogContentImageDto);

    ResponseEntity<Object> getBlog();

    ResponseEntity<Object> deleteBlog(Long blogId);

    ResponseEntity<Object> getBlog(String keyword);

    List<BlogCategoryResponseDto> findAllBlogCategory();
}
