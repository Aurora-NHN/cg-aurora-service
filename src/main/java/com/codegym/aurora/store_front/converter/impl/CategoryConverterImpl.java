package com.codegym.aurora.store_front.converter.impl;

import com.codegym.aurora.store_front.converter.CategoryConverter;
import com.codegym.aurora.store_front.converter.SubCategoryConverter;
import com.codegym.aurora.store_front.entity.Category;
import com.codegym.aurora.store_front.entity.SubCategory;
import com.codegym.aurora.store_front.payload.response.CategoryResponseDTO;
import com.codegym.aurora.store_front.payload.response.SubCategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverterImpl implements CategoryConverter {
    private final SubCategoryConverter subCategoryConverter;

    @Override
    public List<CategoryResponseDTO> convertCategoryEntityToDTO(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            BeanUtils.copyProperties(category, categoryResponseDTO);
            List<SubCategoryResponseDTO> subCategoryDTOList = subCategoryConverter.convertToListSubCategoryDTO(category.getSubCategoryList());
            categoryResponseDTO.setSubCategoryList(subCategoryDTOList);
            categoryResponseDTOList.add(categoryResponseDTO);
        }
        return categoryResponseDTOList;
    }
}
