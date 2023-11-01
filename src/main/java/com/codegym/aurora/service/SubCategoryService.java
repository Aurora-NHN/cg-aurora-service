package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface SubCategoryService {

    ResponseDTO findAll(Pageable pageable);
    ResponseDTO create(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate);
    ResponseDTO update(SubCategoryRequestDTO subCategoryRequestDTO);
    ResponseDTO deleteById(Long subCategoryId);
    ResponseDTO findSubCategoryByIdDeleteFalse(Long subCategoryId);

    ResponseDTO findAllByActiveTrue();
}
