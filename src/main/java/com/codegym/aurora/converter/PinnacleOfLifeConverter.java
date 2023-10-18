package com.codegym.aurora.converter;

import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;

import java.util.List;

public interface PinnacleOfLifeConverter {
    List<PinnacleOfLifeResponseDTOForReport> convertEntityToResponseDto(List<PinnacleOfLife> pinnacleOfLifeList);
}
