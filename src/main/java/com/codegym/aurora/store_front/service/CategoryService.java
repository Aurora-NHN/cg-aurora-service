package com.codegym.aurora.store_front.service;

import com.codegym.aurora.store_front.payload.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<CategoryResponseDTO> findListCategoryResponseDTO();

    }
