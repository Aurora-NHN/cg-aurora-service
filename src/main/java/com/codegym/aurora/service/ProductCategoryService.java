package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

import java.util.List;
public interface ProductCategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();

    ResponseDTO findByActiveTrue();
    ResponseDTO create(CategoryRequestDTO categoryRequestDTO);

    ResponseDTO update(CategoryRequestDTO categoryRequestDTO);
    ResponseDTO deleteById(Long categoryId);
    ResponseDTO activeById(Long categoryId);
    ResponseDTO unactiveById(Long categoryId);

    ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId);
}
