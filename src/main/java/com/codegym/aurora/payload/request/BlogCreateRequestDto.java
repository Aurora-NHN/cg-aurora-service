package com.codegym.aurora.payload.request;

import lombok.Data;

@Data
public class BlogCreateRequestDto {
    private String title;
    private String author;
    private String content;
    private Boolean publish;
}
