package com.codegym.aurora.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class BlogContentImageDto {
    @NotBlank
    private Long id;
    @NotBlank
    private MultipartFile file;
}
