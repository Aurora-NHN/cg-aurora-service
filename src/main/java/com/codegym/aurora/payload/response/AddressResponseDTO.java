package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    private String consigneeName;

    private String phoneNumber;

    private String city;

    private String district;

    private String ward;

    private String deliveryAddress;

    private String additionalInformation;

    private long deliveryCharges;
}
