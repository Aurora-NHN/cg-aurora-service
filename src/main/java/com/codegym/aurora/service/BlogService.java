package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.BlogContentImageDto;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import org.springframework.http.ResponseEntity;

public interface BlogService {

    ResponseEntity<Object> save(BlogCreateRequestDto blogCreateRequestDto);
    ResponseEntity<Object> save(BlogUpdateRequestDto blogUpdateRequestDto);

    ResponseEntity<Object> uploadContentImage(BlogContentImageDto blogContentImageDto);

    ResponseEntity<Object> getBlog();

    ResponseEntity<Object> deleteBlog(Long blogId);
}
