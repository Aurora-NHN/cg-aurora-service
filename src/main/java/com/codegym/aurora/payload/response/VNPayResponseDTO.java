package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VNPayResponseDTO {

    private String oderInfo;

    private String paymentTime;

    private String transactionId;

    private String totalPrice;

    private boolean status;
}
