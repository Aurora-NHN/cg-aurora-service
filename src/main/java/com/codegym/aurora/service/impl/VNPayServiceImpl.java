package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.PaymentCache;
import com.codegym.aurora.configuration.EnvVariable;
import com.codegym.aurora.configuration.VNPayConfiguration;
import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.request.BuyVipRequestDTO;
import com.codegym.aurora.payload.request.VNPayRequestDTO;
import com.codegym.aurora.payload.response.VNPayResponseDTO;
import com.codegym.aurora.repository.HistoryPaymentRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.service.VNPayService;
import com.codegym.aurora.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VNPayServiceImpl implements VNPayService {

    private final VNPayConfiguration vnPayConfiguration;

    private final UserRepository userRepository;

    private final HistoryPaymentRepository historyPaymentRepository;

    private final PaymentCache paymentCache;

    private final UserService userService;

    public static final String VIP = "25000000";

    public static final String VIP_PRO = "65000000";

    public static final String VIP_SUPER = "90000000";

    @Override
    public String createOrder(BuyVipRequestDTO buyVipRequestDTO) {
        String username = userService.getCurrentUsername();
        UUID uuid = UUID.randomUUID();
        paymentCache.addPaymentId(username, uuid);
        HistoryPayment historyPayment = new HistoryPayment();
        historyPayment.setPaymentId(uuid);
        historyPayment.setUser(userRepository.findByUsername(username));
        historyPayment.setStatus(false);

        String amount = null;
        String totalPrice = null;
        String orderInfo = null;
        switch (buyVipRequestDTO.getVipPack()){
            case 1:
                amount = VIP;
                totalPrice = "250000";
                orderInfo = Constant.BUY_VIP;
                break;
            case 2:
                amount = VIP_PRO;
                totalPrice = "650000";
                orderInfo = Constant.BUY_VIP_PRO;
                break;
            case 3:
                amount = VIP_SUPER;
                totalPrice = "900000";
                orderInfo = Constant.BUY_VIP_SUPER;
                break;
        }
        historyPayment.setOderInfo(orderInfo);
        historyPayment.setTotalPrice(totalPrice);

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
        vnp_Params.put("vnp_Amount", amount);
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", uuid.toString());
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        vnp_Params.put("vnp_ReturnUrl", EnvVariable.VN_PAY_RETURN_URL);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        historyPayment.setPaymentTime(vnp_CreateDate);

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
        historyPaymentRepository.save(historyPayment);
        return paymentUrl;
    }

    @Override
    public VNPayResponseDTO oderReturn(VNPayRequestDTO vnPayRequestDTO) {
        String username = userService.getCurrentUsername();
        User user = userRepository.findByUsername(username);
        UUID uuid = paymentCache.getPaymentId(username);
        UserDetail userDetail = user.getUserDetail();
        VNPayResponseDTO vnPayResponseDTO = new VNPayResponseDTO();
        Long price = Long.parseLong(vnPayRequestDTO.getTotalPrice()) / 100;
        vnPayResponseDTO.setOderInfo(vnPayResponseDTO.getOderInfo());
        vnPayResponseDTO.setPaymentTime(vnPayRequestDTO.getPaymentTime());
        vnPayResponseDTO.setFullName(userDetail.getFullName());
        vnPayResponseDTO.setTotalPrice(price.toString());
        vnPayResponseDTO.setStatus(vnPayResponseDTO.isStatus());
        vnPayResponseDTO.setOrderId(uuid);
        if(vnPayRequestDTO.isTransactionStatus()){
//            setVip(price.toString());
        }
//        saveHistoryPayment(vnPayResponseDTO, vnPayRequestDTO.getTransactionId());
        return vnPayResponseDTO;
    }

    @Override
    public void setVip(String price, User user){
        user.setVip(true);
        switch (price){
            case VIP:
                user.setCount(1);
                break;
            case VIP_PRO:
                user.setCount(3);
                break;
            case VIP_SUPER:
                user.setCount(5);
                break;
            default:
                user.setCount(0);
        }
        userRepository.save(user);
    }

    @Override
    public void checkBill(Map<String, String[]> params) {
//        Set<String> stringSet = params.keySet();
//        stringSet.forEach(key -> {
//            System.out.print(key);
//            System.out.println(params.get(key)[0]);
//        });
        String paymentInfo = Arrays.toString(params.get("vnp_OrderInfo"));
        String amount = Arrays.toString(params.get("vnp_Amount"));
        String status = Arrays.toString(params.get("vnp_TransactionStatus"));
        String transactionId = Arrays.toString(params.get("vnp_TransactionNo"));
        UUID paymentId = UUID.fromString(paymentInfo);
        String username = paymentCache.getUsername(paymentId);
        User user = userRepository.findByUsername(username);
        HistoryPayment historyPayment = historyPaymentRepository.findByUser(user);
        String totalPrice = historyPayment.getTotalPrice() + "00";
        if(paymentId.equals(historyPayment.getPaymentId()) && amount.equals(totalPrice) && status.equals("00")){
            setVip(amount, user);
            historyPayment.setStatus(true);
        }
        historyPayment.setTransactionId(transactionId);
        historyPaymentRepository.save(historyPayment);
    }

}
