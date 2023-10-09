package com.codegym.aurora.converter;


import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;

import java.util.List;

public interface SubCategoryConverter {
    List<SubCategoryResponseDTO> convertToListSubCategoryDTO(List<SubCategory> subCategoryList);
    SubCategoryResponseDTO convertEntityToSubCategoryResponeDto(SubCategory subCategory);
    SubCategory convertSubCategoryRequestDtoToEntity(SubCategoryRequestDTO subCategoryRequestDTO);
    SubCategory convertSubCategoryRequestDtoForCreateToEntity(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate);
}
