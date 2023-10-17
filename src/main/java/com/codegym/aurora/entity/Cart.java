package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private long totalAmount;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<CartLine> cartLineList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
