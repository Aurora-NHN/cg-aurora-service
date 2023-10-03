package com.codegym.aurora.store_front.service.impl;
import com.codegym.aurora.store_front.converter.CategoryConverter;
import com.codegym.aurora.store_front.entity.Category;
import com.codegym.aurora.store_front.payload.response.CategoryResponseDTO;
import com.codegym.aurora.store_front.repository.CategoryRepository;
import com.codegym.aurora.store_front.service.CategoryService;
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
