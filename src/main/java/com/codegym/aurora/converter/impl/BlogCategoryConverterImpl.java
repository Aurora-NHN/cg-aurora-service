package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.BlogCategoryConverter;
import com.codegym.aurora.entity.BlogCategory;
import com.codegym.aurora.payload.response.BlogCategoryResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogCategoryConverterImpl implements BlogCategoryConverter {
    @Override
    public BlogCategoryResponseDto convert(BlogCategory blogCategory) {
        BlogCategoryResponseDto responseDto = new BlogCategoryResponseDto();
        BeanUtils.copyProperties(blogCategory, responseDto);
        return responseDto;
    }

    @Override
    public List<BlogCategoryResponseDto> convert(List<BlogCategory> categories) {
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}
