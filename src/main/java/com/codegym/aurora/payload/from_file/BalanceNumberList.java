package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class BalanceNumberList {
    List<BalanceNumberResponseDTO> items;
}
