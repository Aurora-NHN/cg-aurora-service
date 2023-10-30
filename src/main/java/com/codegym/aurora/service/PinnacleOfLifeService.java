package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;

import java.util.List;

public interface PinnacleOfLifeService {

    PinnacleOfLifeResponseDTO getPinnacleOfLifeResponseDtoInStaticList(Integer pinnacleOfLifeNumber);
    Integer calculatePinnacleOfLifeFirst(DataNumerologyReport data);
    Integer calculatePinnacleOfLifeSecond(DataNumerologyReport data);
    Integer calculatePinnacleOfLifeThird(Integer pinnacleOfLifeFirst, Integer pinnacleOfLifeSecond );
    Integer calculatePinnacleOfLifeFour(DataNumerologyReport data);

    Integer calculateAgeForPinnacleOfLifeFirst(Integer lifePathNumber);
    List<PinnacleOfLifeResponseDTO> getPinnacleOfLifeListInStatic(DataNumerologyReport data);
    List<PinnacleOfLifeResponseDTOForReport> createPinnacleOfLifeList(List<PinnacleOfLifeResponseDTO> dtoList, Integer lifePathNumber);
    List<PinnacleOfLifeResponseDTOForReport> getPinnacleOfLifeList(DataNumerologyReport data, Integer lifePathNumber);

}
