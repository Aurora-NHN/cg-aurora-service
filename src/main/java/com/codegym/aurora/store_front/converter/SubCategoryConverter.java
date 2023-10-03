package com.codegym.aurora.store_front.converter;

import com.codegym.aurora.store_front.entity.SubCategory;
import com.codegym.aurora.store_front.payload.response.SubCategoryResponseDTO;

import java.util.List;

public interface SubCategoryConverter {
    List<SubCategoryResponseDTO> convertToListSubCategoryDTO(List<SubCategory> subCategoryList);
}
