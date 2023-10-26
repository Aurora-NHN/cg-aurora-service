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
import java.util.ArrayList;
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
        Blog blog = blogConverter.convert(blogCreateRequestDto);
        blogRepository.save(blog);
        BlogResponseDto blogResponseDto = blogConverter.convert(blog);
        return new ResponseEntity<>(blogResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> save(BlogUpdateRequestDto blogUpdateRequestDto) {
        if (blogUpdateRequestDto.getMainImage() != null){
            Blog savedBlog = blogRepository.findById(blogUpdateRequestDto.getId()).orElse(null);
            if (savedBlog == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            try {
                imageService.delete(savedBlog.getMainImageFilename());
            } catch (IOException e) {
                System.err.println("Error! Undeleted image: " + savedBlog.getMainImageFilename());
            }
        }
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
                return new ResponseEntity<>("Upload image failed!", HttpStatus.BAD_REQUEST);
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

    @Override
    public ResponseEntity<Object> getBlog() {
        List<Blog> blogs = blogRepository.findAllByPublishIsTrue();
        List<BlogResponseDto> responseDtoList = blogConverter.convert(blogs);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<BlogContentImage> contentImages = blog.getBlogContentImages();

        List<BlogContentImage> unDeletedImages = deleteBlogImages(contentImages);

        try {
            imageService.delete(blog.getMainImageFilename());
        } catch (IOException e) {
            System.err.println("Error! Undeleted image: " + blog.getMainImageFilename());
        }

        if (!unDeletedImages.isEmpty()) {
            blog.setBlogContentImages(unDeletedImages);
            blogRepository.save(blog);
            return new ResponseEntity<>("Some image failed to delete! Try again later!", HttpStatus.OK);
        } else {
            blogRepository.delete(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    private List<BlogContentImage> deleteBlogImages(List<BlogContentImage> contentImages) {
        List<BlogContentImage> unDeletedImages = new ArrayList<>();

        for (BlogContentImage contentImage : contentImages) {
            String image = contentImage.getImageFileName();
            try {
                imageService.delete(image);
            } catch (IOException e) {
                System.out.println("Fail to delete: " + image);
                unDeletedImages.add(contentImage);
            }
        }

        return unDeletedImages;
    }
}
