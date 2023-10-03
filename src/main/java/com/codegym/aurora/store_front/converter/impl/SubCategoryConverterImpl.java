package com.codegym.aurora.store_front.converter.impl;

import com.codegym.aurora.store_front.converter.SubCategoryConverter;
import com.codegym.aurora.store_front.entity.SubCategory;
import com.codegym.aurora.store_front.payload.response.SubCategoryResponseDTO;
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
