package com.codegym.aurora.converter;

import com.codegym.aurora.entity.Blog;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import com.codegym.aurora.payload.response.BlogResponseDto;

import java.io.IOException;

public interface BlogConverter {
    Blog convert(BlogCreateRequestDto blogCreateRequestDto) throws IOException;
    Blog convert(BlogUpdateRequestDto blogUpdateRequestDto) throws IOException;
    BlogResponseDto convert(Blog blog);
}
