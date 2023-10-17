package com.codegym.aurora.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyVipRequestDTO {

    @Min(1)
    @Max(3)
    @NotNull
    private Integer vipPack;
}
