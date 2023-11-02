package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequestDtoForCreate {

    @NotBlank
    @Size(max = 255)
    private String name;
    @NotNull
    private Long categoryId;
    private String description;
    private MultipartFile thumbFile;
    private Boolean active;
}
