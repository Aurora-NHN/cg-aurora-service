package com.codegym.aurora.controller.store_front;


import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryDTO(){
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.findListCategoryResponseDTO();
        if (categoryResponseDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoryResponseDTOList);
        }
    }
}
