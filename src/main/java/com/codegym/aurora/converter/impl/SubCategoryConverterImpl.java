package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.Category;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
import com.codegym.aurora.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubCategoryConverterImpl implements SubCategoryConverter {
    private  final CategoryRepository categoryRepository;
    @Override
    public List<SubCategoryResponseDTO> convertToListSubCategoryDTO(List<SubCategory> subCategoryList) {
        List<SubCategoryResponseDTO> subCategoryResponseDTOS = new ArrayList<>();

        for(SubCategory subCategory: subCategoryList){
            SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
            BeanUtils.copyProperties(subCategory,subCategoryResponseDTO);
            subCategoryResponseDTOS.add(subCategoryResponseDTO);
        }
        return subCategoryResponseDTOS;
    }
    @Override
    public SubCategoryResponseDTO convertEntityToSubCategoryResponeDto(SubCategory subCategory) {
        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
        BeanUtils.copyProperties(subCategory, subCategoryResponseDTO);
        Category category = subCategory.getCategory();
        subCategoryResponseDTO.setCategoryId(category.getId());
        subCategoryResponseDTO.setCategoryName(category.getName());
        subCategoryResponseDTO.setProductTypeCount((long) subCategory.getProducts().size());
        return subCategoryResponseDTO;
    }

    @Override
    public SubCategory convertSubCategoryRequestDtoToEntity(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = new SubCategory();
        Category category = categoryRepository.findById(subCategoryRequestDTO.getCategoryId()).orElseThrow();
        subCategory.setId(subCategoryRequestDTO.getId());
        subCategory.setName(subCategoryRequestDTO.getName());
        subCategory.setActive(subCategory.getActive());
        subCategory.setIsDelete(subCategoryRequestDTO.isDelete());
        subCategory.setCategory(category);
        return subCategory;
    }

    @Override
    public SubCategory convertSubCategoryRequestDtoForCreateToEntity(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate) {
        SubCategory subCategory = new SubCategory();
        Category category = categoryRepository.findById(subCategoryRequestDtoForCreate.getCategoryId()).orElseThrow();
        subCategory.setName(subCategoryRequestDtoForCreate.getName());
        subCategory.setCategory(category);
        return subCategory;
    }

}
