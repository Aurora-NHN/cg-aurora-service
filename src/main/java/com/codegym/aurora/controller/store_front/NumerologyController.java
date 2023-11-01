package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.NumerologyReportService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/numerology")
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
                createNumerologyReport(numerologyReportRequestDTO);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }
    @GetMapping("/history")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<NumerologyReportResponseDTO>> getNumerologyReportsPage(@PageableDefault(size = 5) Pageable pageable){
        Page<NumerologyReportResponseDTO> pageNumerologyReports = numerologyReportService.getNumerologyReportsPage(pageable);
        return new ResponseEntity<>(pageNumerologyReports,HttpStatus.OK);
    }


}
