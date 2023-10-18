package com.codegym.aurora.payload.from_file;

import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class MiddleAgedNumberList {
    List<MiddleAgedNumberResponseDto> items;
}
