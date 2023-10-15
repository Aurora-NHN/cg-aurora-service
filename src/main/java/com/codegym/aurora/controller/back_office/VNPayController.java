package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.cache.PaymentCache;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.repository.HistoryPaymentRepository;
import com.codegym.aurora.service.VNPayService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VNPayController {

    private final VNPayService vnPayService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final PaymentCache paymentCache;
    private final HistoryPaymentRepository historyPaymentRepository;

    @PostMapping("/vnpay-order")
    public ResponseEntity<String> submitOder(@Valid @RequestBody BuyVipRequestDTO buyVipRequestDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Constant.BUY_VIP_FAIL, HttpStatus.BAD_REQUEST);
        }
        String vnPayUrl = vnPayService.createOrder(buyVipRequestDTO);
        return new ResponseEntity<>(vnPayUrl, HttpStatus.OK);
    }

//    @GetMapping("/vnpay-payment")
//    public ResponseEntity<VNPayResponseDTO> payment(VNPayRequestDTO vnPayRequestDTO) {
//        VNPayResponseDTO vnPayResponseDTO = vnPayService.oderReturn(vnPayRequestDTO);
//        if (vnPayResponseDTO.isStatus()) {
//            return new ResponseEntity<>(vnPayResponseDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(vnPayResponseDTO, HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("/vnpay/payment-success")
    public void paymentSuccess() throws IOException {
        Map<String, String[]> params = request.getParameterMap();
        vnPayService.checkBill(params);
        response.sendRedirect("http://localhost:3001");
    }
}
