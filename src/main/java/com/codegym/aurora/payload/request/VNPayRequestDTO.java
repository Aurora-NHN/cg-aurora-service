package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VNPayRequestDTO {

    private String amount;

    private String orderInfo;

    private String paymentTime;

    private String transactionId;

    private String totalPrice;

    private boolean transactionStatus;
}
