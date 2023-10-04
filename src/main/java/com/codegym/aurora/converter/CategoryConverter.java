package com.codegym.aurora.converter;


import com.codegym.aurora.entity.Category;
import com.codegym.aurora.payload.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryConverter {
    List<CategoryResponseDTO> convertCategoryEntityToDTO(List<Category> category);

}
