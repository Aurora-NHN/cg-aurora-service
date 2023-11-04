package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.cache.PaymentCache;
import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.payload.request.PaymentRequestDTO;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vnpay")
public class VNPayController {

    private final VNPayService vnPayService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final PaymentCache paymentCache;
    private final HistoryPaymentRepository historyPaymentRepository;

    @PostMapping("/order")
    public ResponseEntity<String> submitOder(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Constant.BUY_VIP_FAIL, HttpStatus.BAD_REQUEST);
        }
        String vnPayUrl = vnPayService.createOrder(paymentRequestDTO);
        return new ResponseEntity<>("Feature currently disabled. Please contact page admin!", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/payment-success")
    public void paymentSuccess() throws IOException {
        try {
            Map<String, String[]> params = request.getParameterMap();
            HistoryPayment historyPayment = vnPayService.checkBill(params);
            boolean status = historyPayment.isStatus();
            String statusPayment;
            if (status){
                statusPayment = Constant.PAYMENT_SUCCESS;
            } else {
                statusPayment = Constant.PAYMENT_FAIL;
            }
            Cookie cookie = new Cookie("paymentStatus", statusPayment);
            cookie.setPath("/");
            cookie.setMaxAge(15);
            response.addCookie(cookie);
            response.sendRedirect("http://localhost:3000");
        } catch (Exception e){
            Cookie cookie = new Cookie("paymentStatus", Constant.PAYMENT_FAIL);
            cookie.setPath("/");
            cookie.setMaxAge(15);
            response.addCookie(cookie);
            response.sendRedirect("http://localhost:3000");
        }
    }

}
