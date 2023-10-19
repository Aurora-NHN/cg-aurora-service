package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.NumerologyReportService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/front-store/numerology")
public class NumerologyController {
    private final NumerologyReportService numerologyReportService;
    @PostMapping
    public ResponseEntity<?> createNumerologyReportFree(
            @Valid @RequestBody NumerologyReportRequestDTO numerologyReportRequestDTO,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseDTO responseDTO = numerologyReportService.
                createFreeNumerologyReportResponeDTO(numerologyReportRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/full-vip")
//    public ResponseEntity<?> createNumerologyReportFullNumber(
//            @Valid @RequestBody NumerologyReportRequestDTO numerologyReportRequestDTO,
//            BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
//        }
//        if (numerologyReportService.checkCount() == 0){
//            return new ResponseEntity<>(Constant.COUNT_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
//        }
//        ResponseDTO responseDTO = numerologyReportService.
//                createFullNumerologyReportResponseDTO(numerologyReportRequestDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//    }

}
