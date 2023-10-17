package com.codegym.aurora.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String description;
    private String mainImageUrl;
    private Boolean publish;
    private LocalDateTime createdAt;
}
