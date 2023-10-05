package com.codegym.aurora.service;

import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.ResponseDTO;

public interface SubCategoryService {

    ResponseDTO findByActiveTrue();
    ResponseDTO create(SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate);
    ResponseDTO update(SubCategoryRequestDTO subCategoryRequestDTO);
    ResponseDTO deleteById(Long subCategoryId);
    ResponseDTO activeById(Long subCategoryId);
    ResponseDTO unactiveById(Long subCategoryId);
    ResponseDTO findSubCategoryByIdDeleteFalse(Long subCategoryId);
}
