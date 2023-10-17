package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.BlogConverter;
import com.codegym.aurora.entity.Blog;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import com.codegym.aurora.payload.response.BlogResponseDto;
import com.codegym.aurora.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BlogConverterImpl implements BlogConverter {
    private final ImageService imageService;

    @Override
    public Blog convert(BlogCreateRequestDto blogCreateRequestDto) throws IOException {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogCreateRequestDto, blog);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setPublish(false);
        MultipartFile imageFile = blogCreateRequestDto.getMainImage();
        if (imageFile != null && !imageFile.isEmpty()){
            String imageFilename = imageService.save(imageFile);
            blog.setMainImageFilename(imageFilename);
        }
        return blog;
    }

    public Blog convert(BlogUpdateRequestDto blogUpdateRequestDto) throws IOException {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogUpdateRequestDto, blog);
        blog.setCreatedAt(LocalDateTime.now());
        MultipartFile imageFile = blogUpdateRequestDto.getMainImage();
        if (imageFile != null && !imageFile.isEmpty()){
            String imageFilename = imageService.save(imageFile);
            blog.setMainImageFilename(imageFilename);
        }
        return blog;
    }

    @Override
    public BlogResponseDto convert(Blog blog) {
        BlogResponseDto blogResponseDto = new BlogResponseDto();
        BeanUtils.copyProperties(blog, blogResponseDto);
        return blogResponseDto;
    }
}
