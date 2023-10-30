package com.codegym.aurora.converter;

import com.codegym.aurora.entity.BlogCategory;
import com.codegym.aurora.payload.response.BlogCategoryResponseDto;

import java.util.List;

public interface BlogCategoryConverter {
    BlogCategoryResponseDto convert(BlogCategory blogCategory);
    List<BlogCategoryResponseDto> convert(List<BlogCategory> categories);
}
