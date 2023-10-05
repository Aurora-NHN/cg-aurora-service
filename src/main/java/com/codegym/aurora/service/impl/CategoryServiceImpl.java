package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.CategoryConverter;
import com.codegym.aurora.entity.Category;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.repository.CategoryRepository;
import com.codegym.aurora.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    public List<CategoryResponseDTO> findListCategoryResponseDTO(){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOList = categoryConverter.convertCategoryEntityToDTO(categoryList);
        return categoryResponseDTOList;
    }
}
