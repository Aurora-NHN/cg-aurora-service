package com.codegym.aurora.service;


import com.codegym.aurora.payload.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();

    }
