package com.codegym.aurora.store_front.controller;

import com.codegym.aurora.store_front.payload.response.CategoryResponseDTO;
import com.codegym.aurora.store_front.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/category")
    public List<CategoryResponseDTO> getCategoryDTO(){
        return categoryService.findListCategoryResponseDTO();
    }
}
