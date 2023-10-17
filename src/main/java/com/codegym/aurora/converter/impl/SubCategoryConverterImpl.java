package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.ProductCategory;
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
        return subCategoryResponseDTO;
    }

    @Override
    public SubCategory convertSubCategoryRequestDtoToEntity(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = new SubCategory();
        ProductCategory productCategory = categoryRepository.findById(subCategoryRequestDTO.getCategoryId()).orElseThrow();
        subCategory.setId(subCategoryRequestDTO.getId());
        subCategory.setName(subCategoryRequestDTO.getName());
        subCategory.setActivated(subCategory.isActivated());
        subCategory.setDelete(subCategoryRequestDTO.isDelete());
        subCategory.setProductCategory(productCategory);
        return subCategory;
    }

    @Override
    public SubCategory convertSubCategoryRequestDtoForCreateToEntity(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate) {
        SubCategory subCategory = new SubCategory();
        ProductCategory productCategory = categoryRepository.findById(subCategoryRequestDtoForCreate.getCategoryId()).orElseThrow();
        subCategory.setName(subCategoryRequestDtoForCreate.getName());
        subCategory.setProductCategory(productCategory);
        return subCategory;
    }

}
