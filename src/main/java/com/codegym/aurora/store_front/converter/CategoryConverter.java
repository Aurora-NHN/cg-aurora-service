package com.codegym.aurora.store_front.converter;

import com.codegym.aurora.store_front.entity.Category;
import com.codegym.aurora.store_front.payload.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryConverter {
    List<CategoryResponseDTO> convertCategoryEntityToDTO(List<Category> category);

}
