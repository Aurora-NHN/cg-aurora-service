package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.from_file.PinnacleOfLifeList;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTOForReport;
import com.codegym.aurora.service.PinnacleOfLifeService;
import com.codegym.aurora.util.NumeroloryConstants;
import com.codegym.aurora.util.NumeroloryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PinnacleOfLifeServiceImpl implements PinnacleOfLifeService {

    private static List<PinnacleOfLifeResponseDTO> staticPinnacleOfLifeList = new ArrayList<>();
    private static final Integer SPECIAL_NUMBER_FOR_CALCULATE_AGE = 36;
    private static final Integer AGE_CYCLE = 9;
    private static final  Integer SPECIAL_PINNACLE_OF_LIFE_NUMBER_10 = 10;
    private static final  Integer SPECIAL_PINNACLE_OF_LIFE_NUMBER_11 = 11;
    static {
        try {
            staticPinnacleOfLifeList= loadStaticPinnacleOfLifeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static List<PinnacleOfLifeResponseDTO> loadStaticPinnacleOfLifeList() throws Exception {
        try (InputStream inputStream = new ClassPathResource("numerology/pinnacle-of-life.json").getInputStream()) {
            PinnacleOfLifeList pinnacleOfLifeList = new ObjectMapper().readValue(inputStream, PinnacleOfLifeList.class);
            return pinnacleOfLifeList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public PinnacleOfLifeResponseDTO getPinnacleOfLifeResponseDtoInStaticList(Integer pinnacleOfLifeNumber) {
        PinnacleOfLifeResponseDTO result = staticPinnacleOfLifeList.stream()
                .filter(dto -> dto.getNumber() == pinnacleOfLifeNumber)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy số số đỉnh cao phù hợp phù hợp"));
        return result;
    }

    @Override
    public Integer calculatePinnacleOfLifeFirst(DataNumerologyReport data) {
        Integer monthReduce = NumeroloryUtils.reduceToSingleDigit(data.getMonthOfBirth());
        Integer dayReduce = NumeroloryUtils.reduceToSingleDigit(data.getDayOfBirth());
        return NumeroloryUtils.reduceToSingleDigit(monthReduce + dayReduce);
    }

    @Override
    public Integer calculatePinnacleOfLifeSecond(DataNumerologyReport data) {
        Integer dayReduce = NumeroloryUtils.reduceToSingleDigit(data.getDayOfBirth());
        Integer yearReduce = NumeroloryUtils.reduceToSingleDigit(data.getYearOfBirth());
        return NumeroloryUtils.reduceToSingleDigit(dayReduce + yearReduce);
    }

    @Override
    public Integer calculatePinnacleOfLifeThird(Integer pinnacleOfLifeFirst, Integer pinnacleOfLifeSecond) {
        Integer sum = pinnacleOfLifeFirst + pinnacleOfLifeSecond;
        if (sum == SPECIAL_PINNACLE_OF_LIFE_NUMBER_10 ||
                sum == SPECIAL_PINNACLE_OF_LIFE_NUMBER_11){
            return sum;
        }
        return NumeroloryUtils.reduceToSingleDigit(sum);
    }

    @Override
    public Integer calculatePinnacleOfLifeFour(DataNumerologyReport data) {
        Integer reduceMonth = NumeroloryUtils.reduceToSingleDigit(data.getMonthOfBirth());
        Integer reduceYear = NumeroloryUtils.reduceToSingleDigit(data.getYearOfBirth());
        Integer sum = reduceMonth + reduceYear;
        if (sum == SPECIAL_PINNACLE_OF_LIFE_NUMBER_10 ||
                sum == SPECIAL_PINNACLE_OF_LIFE_NUMBER_11){
            return sum;
        }
        return NumeroloryUtils.reduceToSingleDigit(sum);
    }

    @Override
    public Integer calculateAgeForPinnacleOfLifeFirst(Integer lifePathNumber) {
        return SPECIAL_NUMBER_FOR_CALCULATE_AGE - lifePathNumber;
    }

    @Override
    public List<PinnacleOfLifeResponseDTO> getPinnacleOfLifeListInStatic(DataNumerologyReport data) {
        List<PinnacleOfLifeResponseDTO> pinnacleOfLifeList = new ArrayList<>();

        Integer pinnacleOfLifeFirstNumber = calculatePinnacleOfLifeFirst(data);
        Integer pinnacleOfLifeSecondNumber = calculatePinnacleOfLifeSecond(data);
        Integer pinnacleOfLifeThirdNumber = calculatePinnacleOfLifeThird(
                pinnacleOfLifeFirstNumber,
                pinnacleOfLifeSecondNumber);
        Integer pinnacleOfLifeFourNumber = calculatePinnacleOfLifeFour(data);

        pinnacleOfLifeList.add(getPinnacleOfLifeResponseDtoInStaticList(pinnacleOfLifeFirstNumber));
        pinnacleOfLifeList.add(getPinnacleOfLifeResponseDtoInStaticList(pinnacleOfLifeSecondNumber));
        pinnacleOfLifeList.add(getPinnacleOfLifeResponseDtoInStaticList(pinnacleOfLifeThirdNumber));
        pinnacleOfLifeList.add(getPinnacleOfLifeResponseDtoInStaticList(pinnacleOfLifeFourNumber));

        return pinnacleOfLifeList;
    }

    @Override
    public List<PinnacleOfLifeResponseDTOForReport> createPinnacleOfLifeList(
            List<PinnacleOfLifeResponseDTO> dtoList, Integer lifePathNumber) {
        List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeListForReport = new ArrayList<>();

        for (int index = 0; index < dtoList.size(); index ++){
            PinnacleOfLifeResponseDTO item = dtoList.get(index);
            PinnacleOfLifeResponseDTOForReport pinnacleOfLifeResponseDTOForReport =
                    new PinnacleOfLifeResponseDTOForReport();
            BeanUtils.copyProperties(item, pinnacleOfLifeResponseDTOForReport);
            Integer age;

            if (index == 0){
                age = calculateAgeForPinnacleOfLifeFirst(lifePathNumber);
            }else {
                age = calculateAgeForPinnacleOfLifeFirst(lifePathNumber) + (index * AGE_CYCLE);
            }
            pinnacleOfLifeResponseDTOForReport.setAge(age);
            pinnacleOfLifeListForReport.add(pinnacleOfLifeResponseDTOForReport);
        }

        return pinnacleOfLifeListForReport;
    }

    @Override
    public List<PinnacleOfLifeResponseDTOForReport> getPinnacleOfLifeList(
            DataNumerologyReport data, Integer lifePathNumber) {
        Integer newLifePathNumber = checkLifePathNumber(lifePathNumber);
        List<PinnacleOfLifeResponseDTO> pinnacleOfLifeList = getPinnacleOfLifeListInStatic(data);
        List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeListForReport = createPinnacleOfLifeList(
                pinnacleOfLifeList, newLifePathNumber);
        return pinnacleOfLifeListForReport;
    }

    private Integer checkLifePathNumber(Integer lifePathNumber){

        if (lifePathNumber == NumeroloryConstants.MASTER_NUMBER_33 ||
            lifePathNumber == NumeroloryConstants.MASTER_NUMBER_22){
            return NumeroloryUtils.reduceNumber(lifePathNumber);
        }
        return lifePathNumber;
    }

}
