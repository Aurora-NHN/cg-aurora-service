package com.codegym.aurora.back_office.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyVipRequestDTO {

    private String token;

    private int count;
}
