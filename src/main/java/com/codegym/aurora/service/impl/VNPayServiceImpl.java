package com.codegym.aurora.service.impl;

import com.codegym.aurora.configuration.EnvVariable;
import com.codegym.aurora.configuration.VNPayConfiguration;
import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;
import com.codegym.aurora.repository.HistoryPaymentRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.VNPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Service
@Transactional
@RequiredArgsConstructor
public class VNPayServiceImpl implements VNPayService {

    private final VNPayConfiguration vnPayConfiguration;

    private final UserRepository userRepository;

    private final HistoryPaymentRepository historyPaymentRepository;

    private UserServiceImpl userService;

    @Override
    public String createOrder(BuyVipRequestDTO buyVipRequestDTO, String baseUrl) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = vnPayConfiguration.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = EnvVariable.VN_PAY_TERMINAL_CODE;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(buyVipRequestDTO.getAmount()*100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", buyVipRequestDTO.getOderInfo());
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        baseUrl += EnvVariable.VN_PAY_RETURN_URL;
        vnp_Params.put("vnp_ReturnUrl", baseUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = vnPayConfiguration.hmacSHA512(EnvVariable.VN_PAY_HASH_SECRET, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = EnvVariable.VN_PAY_URL + "?" + queryUrl;
        return paymentUrl;
    }

    @Override
    public VNPayResponseDTO oderReturn(HttpServletRequest request) {
        VNPayResponseDTO vnPayResponseDTO = new VNPayResponseDTO();
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        vnPayResponseDTO.setOderInfo(orderInfo);
        vnPayResponseDTO.setPaymentTime(paymentTime);
        vnPayResponseDTO.setTransactionId(transactionId);
        vnPayResponseDTO.setTotalPrice(totalPrice);

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = vnPayConfiguration.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                vnPayResponseDTO.setStatus(true);
            }
        }
        vnPayResponseDTO.setStatus(false);
        saveHistoryPayment(vnPayResponseDTO);
        return vnPayResponseDTO;
    }

    void saveHistoryPayment(VNPayResponseDTO vnPayResponseDTO){
        User user = userRepository.findByUsername(userService.getCurrentUsername());
        HistoryPayment historyPayment = new HistoryPayment();
        historyPayment.setPaymentTime(vnPayResponseDTO.getPaymentTime());
        historyPayment.setOderInfo(vnPayResponseDTO.getOderInfo());
        historyPayment.setTotalPrice(vnPayResponseDTO.getTotalPrice());
        historyPayment.setTransactionId(vnPayResponseDTO.getTransactionId());
        historyPayment.setUser(user);
        historyPayment.setStatus(vnPayResponseDTO.isStatus());
        historyPaymentRepository.save(historyPayment);
    }

}
