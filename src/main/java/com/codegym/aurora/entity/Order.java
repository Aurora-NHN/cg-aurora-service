package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private long totalAmount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "EXPECTED_DELIVERY", nullable = false)
    private String expectedDelivery;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE})
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private User user;

    @OneToOne(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Address address ;

}
