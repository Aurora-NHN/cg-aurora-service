package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.CategoryConverter;
import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.Category;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
import com.codegym.aurora.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverterImpl implements CategoryConverter {
    private final SubCategoryConverter subCategoryConverter;
    private final ImageService imageService;

    @Override
    public List<CategoryResponseDTO> convertCategoryEntityToDTO(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            BeanUtils.copyProperties(category, categoryResponseDTO);
            List<SubCategoryResponseDTO> subCategoryDTOList = subCategoryConverter
                    .convertToListSubCategoryDTO(category.getSubCategoryList());
            categoryResponseDTO.setSubCategoryList(subCategoryDTOList);
            categoryResponseDTOList.add(categoryResponseDTO);
        }
        return categoryResponseDTOList;
    }

    @Override
    public Category convertCategoryRequestToEntity(CategoryRequestDTO requestDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(requestDTO, category);
        return category;
    }

    @Override
    public CategoryResponseDTOForAdmin convertEntityToCategoryResponseDTOForAdmin(Category category) {
        CategoryResponseDTOForAdmin categoryResponseDTOForAdmin = new CategoryResponseDTOForAdmin();
        BeanUtils.copyProperties(category, categoryResponseDTOForAdmin);
        String thumb = category.getThumb();
        if (thumb != null) categoryResponseDTOForAdmin.setThumbUrl(imageService.getImageUrl(thumb));

        List<SubCategory> subCategories = category.getSubCategoryList();
        List<String> subCategoriesName = subCategories.stream().map(SubCategory::getName).collect(Collectors.toList());
        categoryResponseDTOForAdmin.setSubCategories(subCategoriesName);
        return categoryResponseDTOForAdmin;
    }
}
