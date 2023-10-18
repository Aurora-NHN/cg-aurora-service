package com.codegym.aurora.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BlogUpdateRequestDto {
    @NotNull
    private Long id;
    @NotBlank(message = "Title can not be blank!")
    private String title;
    @NotBlank(message = "Author can not be blank!")
    private String author;
    private String description;
    private String content;
    private MultipartFile mainImage;
    @NotNull
    private Boolean publish;
}
