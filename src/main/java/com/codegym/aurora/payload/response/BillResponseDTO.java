package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

    private String fullName;

    private String orderInfo;

    private String totalPrice;

    private String paymentTime;

    private String paymentId;
}
