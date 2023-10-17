package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.BlogConverter;
import com.codegym.aurora.entity.Blog;
import com.codegym.aurora.entity.BlogContentImage;
import com.codegym.aurora.payload.request.BlogContentImageDto;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import com.codegym.aurora.payload.response.BlogResponseDto;
import com.codegym.aurora.repository.BlogRepository;
import com.codegym.aurora.service.BlogService;
import com.codegym.aurora.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final ImageService imageService;
    private final BlogConverter blogConverter;

    @Override
    public ResponseEntity<Object> save(BlogCreateRequestDto blogCreateRequestDto) {
        try {
            Blog blog = blogConverter.convert(blogCreateRequestDto);
            blogRepository.save(blog);
            BlogResponseDto blogResponseDto = blogConverter.convert(blog);
            return new ResponseEntity<>(blogResponseDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Create failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> save(BlogUpdateRequestDto blogUpdateRequestDto) {
        try {
            Blog blog = blogConverter.convert(blogUpdateRequestDto);
            blogRepository.save(blog);
            BlogResponseDto blogResponseDto = blogConverter.convert(blog);
            return new ResponseEntity<>(blogResponseDto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Update failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> uploadContentImage(BlogContentImageDto blogContentImageDto) {
        MultipartFile file = blogContentImageDto.getFile();
        try {
            if (file == null || blogContentImageDto.getId() == null) {
                return new ResponseEntity<>("Tat ca la tai Bao Hong",HttpStatus.BAD_REQUEST);
            }
            String fileName = imageService.save(file);
            Blog blog = blogRepository.findById(blogContentImageDto.getId()).orElse(null);
            if (blog == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            BlogContentImage blogContentImage = new BlogContentImage();
            blogContentImage.setBlog(blog);
            blogContentImage.setImageFileName(fileName);
            List<BlogContentImage> imageFiles = blog.getBlogContentImages();
            imageFiles.add(blogContentImage);
            blogRepository.save(blog);

            String contentImageUrl = imageService.getImageUrl(fileName);
            return new ResponseEntity<>(contentImageUrl, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Upload failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
