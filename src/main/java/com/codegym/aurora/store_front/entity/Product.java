package com.codegym.aurora.store_front.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private long price;

    @Column(name = "WEIGHT", nullable = false)
    private int weighT;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "QUANTITY_SOLD", nullable = false)
    private int quantitySold;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "IS_DELETE", nullable = false)
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED", nullable = false)
    private boolean isActivated;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImageUrlList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "SUB_CATEGORY_ID",referencedColumnName = "ID")
    private SubCategory subCategory;

}
