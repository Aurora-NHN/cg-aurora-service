package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;
import com.codegym.aurora.service.VNPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VNPayController {

    private final VNPayService vnPayService;

    @PostMapping("/vnpay-order")
    public String submitOder(@RequestBody BuyVipRequestDTO buyVipRequestDTO, HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnPayUrl = vnPayService.createOrder(buyVipRequestDTO, baseUrl);
        return "redirect:" + vnPayUrl;
    }

    @GetMapping("/vnpay-payment")
    public ResponseEntity<VNPayResponseDTO> payment(HttpServletRequest httpServletRequest){
        VNPayResponseDTO vnPayResponseDTO = vnPayService.oderReturn(httpServletRequest);
        if(vnPayResponseDTO.isStatus()){
            return new ResponseEntity<>(vnPayResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(vnPayResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
