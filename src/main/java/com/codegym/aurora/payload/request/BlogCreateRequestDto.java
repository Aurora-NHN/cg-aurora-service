package com.codegym.aurora.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogCreateRequestDto {
    private String title;
    private String author;
    private String content;
    private MultipartFile mainImage;
    private Boolean publish;
}
