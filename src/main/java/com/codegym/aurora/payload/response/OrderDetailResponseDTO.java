package com.codegym.aurora.payload.response;

import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDTO {
    private Integer quantity;
    private long totalPrice;
    private ProductResponseDTO productResponseDTO;
}
