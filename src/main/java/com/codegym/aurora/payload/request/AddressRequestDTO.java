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

    private String companyName;

    private String deliveryAddress;

    private String additionalInformation;

}
