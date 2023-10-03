package com.codegym.aurora.converter.impl;


import com.codegym.aurora.converter.SubCategoryConverter;
import com.codegym.aurora.entity.SubCategory;
import com.codegym.aurora.payload.response.SubCategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubCategoryConverterImpl implements SubCategoryConverter {
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

}
