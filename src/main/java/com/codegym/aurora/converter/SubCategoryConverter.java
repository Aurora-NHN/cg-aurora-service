package com.codegym.aurora.converter;



import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;

import java.util.List;

public interface SubCategoryConverter {
    List<SubCategoryResponseDTO> convertToListSubCategoryDTO(List<SubCategory> subCategoryList);
}
