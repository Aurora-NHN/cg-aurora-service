package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.response.BlogCategoryResponseDto;
import com.codegym.aurora.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog-categories")
@RequiredArgsConstructor
public class BlogCategoryController {
    private final BlogService blogService;
    @GetMapping
    public ResponseEntity<?> getCategories(){
        List<BlogCategoryResponseDto> responseDtoList = blogService.findAllBlogCategory();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
}
