package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryResponseDTO> getCategoryDTO(){
        return categoryService.findListCategoryResponseDTO();
    }
}
