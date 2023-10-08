package com.codegym.aurora.service;


import com.codegym.aurora.payload.response.CategoryResponseDTO;

import java.util.List;
public interface CategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();

}
