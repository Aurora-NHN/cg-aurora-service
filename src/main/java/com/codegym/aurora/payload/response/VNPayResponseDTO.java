package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VNPayResponseDTO {

    private String fullName;

    private String oderInfo;

    private UUID orderId;

    private String paymentTime;

    private String totalPrice;

    private boolean status;
}
