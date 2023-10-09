package com.codegym.aurora.converter;


import com.codegym.aurora.entity.Category;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;

import java.util.List;

public interface CategoryConverter {
    List<CategoryResponseDTO> convertCategoryEntityToDTO(List<Category> category);
    Category convertCategoryRequestToEntity(CategoryRequestDTO requestDTO);

    CategoryResponseDTOForAdmin convertEntityToCategoryResponseDTOForAdmin(Category category);
}
