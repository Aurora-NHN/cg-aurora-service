package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
    private String consigneeName;

    private String phoneNumber;

    private String city;

    private String deliveryAddress;

    private String additionalInformation;

    private long deliveryCharges;

}
