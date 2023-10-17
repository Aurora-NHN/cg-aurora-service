package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.PinnacleOfLifeConverter;
import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;
import com.codegym.aurora.service.PinnacleOfLifeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PinnacleOfLifeConverterImpl implements PinnacleOfLifeConverter {
    private final PinnacleOfLifeService pinnacleOfLifeService;
    @Override
    public List<PinnacleOfLifeResponseDTOForReport> convertEntityToResponseDto(List<PinnacleOfLife> pinnacleOfLifeList) {
        List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeResponseDTOForReportList = new ArrayList<>();
        for (PinnacleOfLife item: pinnacleOfLifeList){
            PinnacleOfLifeResponseDTOForReport pinnacleOfLifeResponseDTOForReport = new PinnacleOfLifeResponseDTOForReport();
            PinnacleOfLifeResponseDTO pinnacleOfLifeResponseDTO = pinnacleOfLifeService
                    .getPinnacleOfLifeResponseDtoInStaticList(item.getPinnacleOfLifeNumber());
//            BeanUtils.copyProperties(pinnacleOfLifeResponseDTOForReport, pinnacleOfLifeResponseDTO);
            BeanUtils.copyProperties(pinnacleOfLifeResponseDTO, pinnacleOfLifeResponseDTOForReport);
            pinnacleOfLifeResponseDTOForReport.setAge(item.getAge());
            pinnacleOfLifeResponseDTOForReportList.add(pinnacleOfLifeResponseDTOForReport);
        }
        return pinnacleOfLifeResponseDTOForReportList;
    }
}
