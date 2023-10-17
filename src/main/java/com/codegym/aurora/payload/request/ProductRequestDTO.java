package com.codegym.aurora.payload.request;

import com.codegym.aurora.entity.CartLine;
import com.codegym.aurora.entity.OrderDetail;
import com.codegym.aurora.entity.ProductImage;
import com.codegym.aurora.entity.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String name;

    private long price;

    private int weight;

    private int quantity;

    private String description;

    private int quantitySold;

    private MultipartFile image;

    private List<MultipartFile> productImageList = new ArrayList<>();

    private SubCategory subCategory;

    private String author;

    private String include;

    private String producer;

}
