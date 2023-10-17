package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.CategoryConverter;
import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.ProductCategory;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTOForAdmin;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
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
    public List<CategoryResponseDTO> convertCategoryEntityToDTO(List<ProductCategory> productCategoryList) {
        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            BeanUtils.copyProperties(productCategory, categoryResponseDTO);
            List<SubCategoryResponseDTO> subCategoryDTOList = subCategoryConverter
                    .convertToListSubCategoryDTO(productCategory.getSubCategoryList());
            categoryResponseDTO.setSubCategoryList(subCategoryDTOList);
            categoryResponseDTOList.add(categoryResponseDTO);
        }
        return categoryResponseDTOList;
    }

    @Override
    public ProductCategory convertCategoryRequestToEntity(CategoryRequestDTO requestDTO) {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(requestDTO, productCategory);
        return productCategory;
    }

    @Override
    public CategoryResponseDTOForAdmin convertEntityToCategoryResponseDTOForAdmin(ProductCategory productCategory) {
        CategoryResponseDTOForAdmin categoryResponseDTOForAdmin = new CategoryResponseDTOForAdmin();
        BeanUtils.copyProperties(productCategory, categoryResponseDTOForAdmin);
        return categoryResponseDTOForAdmin;
    }
}
