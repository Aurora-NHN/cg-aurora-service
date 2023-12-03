package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "HISTORY_PAYMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HistoryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PAYMENT_ID")
//    @Type(type = "uuid-char")
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String paymentId;

    @Column(name = "ORDER_INFO")
    private String oderInfo;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "PAYMENT_TIME")
    private String paymentTime;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "TOTAL_PRICE")
    private String totalPrice;

    @Column(name = "IS_DELETE", columnDefinition = "boolean default false")
    private boolean isDelete;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
