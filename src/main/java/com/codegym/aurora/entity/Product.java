package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private int weight;

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

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ProductImage> productImageUrlList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "SUB_CATEGORY_ID",referencedColumnName = "ID")
    private SubCategory subCategory;

}
