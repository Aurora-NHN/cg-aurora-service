package com.codegym.aurora.service;


import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.CategoryResponseDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();

    ResponseDTO findByActiveTrue();
    ResponseDTO create(CategoryRequestDTO categoryRequestDTO);

    ResponseDTO update(CategoryRequestDTO categoryRequestDTO);
    ResponseDTO deleteById(Long categoryId);
    ResponseDTO activeById(Long categoryId);
    ResponseDTO unactiveById(Long categoryId);

    ResponseDTO findCategoryByIdAndDeteteFalse(Long categoryId);
}
