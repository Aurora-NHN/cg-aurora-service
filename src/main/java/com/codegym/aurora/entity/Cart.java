package com.codegym.aurora.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<CartLine> cartLineList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
