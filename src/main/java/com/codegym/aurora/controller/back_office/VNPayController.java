package com.codegym.aurora.controller.back_office;

import com.codegym.aurora.cache.PaymentCache;
import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.payload.request.PaymentRequestDTO;
import com.codegym.aurora.service.VNPayService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public static final Logger LOGGER = LogManager.getLogger(VNPayController.class);
    private final VNPayService vnPayService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final PaymentCache paymentCache;

    @PostMapping("/order")
    public ResponseEntity<String> submitOder(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Constant.BUY_VIP_FAIL, HttpStatus.BAD_REQUEST);
        }
        String vnPayUrl = vnPayService.createOrder(paymentRequestDTO, request);
        if (paymentCache.getActiveVnPayService()) {
            return new ResponseEntity<>(vnPayUrl, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Feature currently disabled. Please contact page admin!"
                    , HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/set-active-status")
    public ResponseEntity<?> setServiceStatus(@RequestParam Boolean value) {
        paymentCache.setActiveVnPayService(value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/payment-success")
    public void paymentSuccess() throws IOException {
        try {
            Map<String, String[]> params = request.getParameterMap();
            HistoryPayment historyPayment = vnPayService.checkBill(params);
            boolean status = historyPayment.isStatus();

            LOGGER.info("Payment status: " + (status?"[Success!]":"[Fail!]")
                    + "-" + params.get("vnp_OrderInfo")[0]
                    + "-" + params.get("vnp_Amount")[0]);

            Cookie cookie = new Cookie("paymentStatus", ""+status);
            cookie.setPath("/");
            cookie.setMaxAge(15);
            response.addCookie(cookie);
            response.sendRedirect("https://cg-aurora-app.vercel.app/");
//            response.sendRedirect("http://localhost:3000/");
        } catch (Exception e) {
            e.printStackTrace();
            Cookie cookie = new Cookie("paymentStatus", Constant.PAYMENT_FAIL);
            cookie.setPath("/");
            cookie.setMaxAge(15);
            response.addCookie(cookie);
            response.sendRedirect("https://cg-aurora-app.vercel.app/");
        }
    }

}
