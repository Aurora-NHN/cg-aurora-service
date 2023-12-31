package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;

import java.util.List;
public interface CategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();
    ResponseDTO findAll();
    ResponseDTO create(CategoryRequestDTO categoryRequestDTO);
    ResponseDTO update(CategoryRequestDTO categoryRequestDTO);
    ResponseDTO deleteById(Long categoryId);
    ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId);
}
