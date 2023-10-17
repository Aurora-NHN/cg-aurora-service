package com.codegym.aurora.converter;


import com.codegym.aurora.entity.ProductCategory;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;

import java.util.List;

public interface CategoryConverter {
    List<CategoryResponseDTO> convertCategoryEntityToDTO(List<ProductCategory> productCategory);
    ProductCategory convertCategoryRequestToEntity(CategoryRequestDTO requestDTO);

    CategoryResponseDTOForAdmin convertEntityToCategoryResponseDTOForAdmin(ProductCategory productCategory);
}
