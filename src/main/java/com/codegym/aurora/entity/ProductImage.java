package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_IMAGE")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "IS_DELETE", nullable = false)
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED", nullable = false)
    private boolean isActivated;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "ID")
    private Product product;

}
