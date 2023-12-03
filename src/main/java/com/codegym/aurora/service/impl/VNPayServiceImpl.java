package com.codegym.aurora.service.impl;

import com.codegym.aurora.cache.CartCache;
import com.codegym.aurora.cache.OrderCache;
import com.codegym.aurora.cache.PaymentCache;
import com.codegym.aurora.configuration.EnvVariable;
import com.codegym.aurora.configuration.VNPayConfiguration;
import com.codegym.aurora.entity.Cart;
import com.codegym.aurora.entity.HistoryPayment;
import com.codegym.aurora.entity.Order;
import com.codegym.aurora.entity.User;
import com.codegym.aurora.entity.UserDetail;
import com.codegym.aurora.payload.request.PaymentRequestDTO;
import com.codegym.aurora.repository.CartLineRepository;
import com.codegym.aurora.repository.CartRepository;
import com.codegym.aurora.repository.HistoryPaymentRepository;
import com.codegym.aurora.repository.UserRepository;
import com.codegym.aurora.service.ClientService;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.service.VNPayService;
import com.codegym.aurora.util.Constant;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VNPayServiceImpl implements VNPayService {

    public static final String VIP = "25000000";
    public static final String VIP_PRO = "65000000";
    public static final String VIP_SUPER = "90000000";
    private final VNPayConfiguration vnPayConfiguration;
    private final UserRepository userRepository;
    private final HistoryPaymentRepository historyPaymentRepository;
    private final PaymentCache paymentCache;
    private final UserService userService;
    private final ClientService clientService;
    private final OrderCache orderCache;
    private final CartRepository cartRepository;
    private final CartLineRepository cartLineRepository;
    private final CartCache cartCache;

    @Override
    public String createOrder(PaymentRequestDTO paymentRequestDTO, HttpServletRequest request) {
        String username = userService.getCurrentUsername();
        String paymentId = UUID.randomUUID().toString();
        paymentCache.addPaymentId(username, paymentId);
        String totalAmount = null;
        String amount = null;
        String totalPrice = null;
        String orderInfo = null;

        switch (paymentRequestDTO.getVipPack()) {
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
            case 4:
                Order order = orderCache.getOrder(username);
                totalAmount = String.valueOf(order.getTotalAmount());
                amount = totalAmount + "00";
                orderInfo = Constant.PAY_PURCHASE_INVOICE;
        }

        String vnpVersion = "2.1.0";
        String vnpCommand = "pay";
        String vnpIpAddr = vnPayConfiguration.getIpAddress(request);
//        String vnpIpAddr = getClientIP(request);
        String vnpTmnCode = EnvVariable.VN_PAY_TERMINAL_CODE;
        String orderType = "190003";
        String locate = "vn";
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        cld.add(Calendar.HOUR, 7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(cld.getTime());
        cld.add(Calendar.MINUTE, 15);
        String vnpExpireDate = formatter.format(cld.getTime());

        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", vnpVersion);
        vnpParams.put("vnp_Command", vnpCommand);
        vnpParams.put("vnp_TmnCode", vnpTmnCode);
        vnpParams.put("vnp_Amount", amount);
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", paymentId);
        vnpParams.put("vnp_OrderInfo", orderInfo);
        vnpParams.put("vnp_OrderType", orderType);
        vnpParams.put("vnp_Locale", locate);
        vnpParams.put("vnp_ReturnUrl", EnvVariable.VN_PAY_RETURN_URL);
        vnpParams.put("vnp_IpAddr", vnpIpAddr);
        vnpParams.put("vnp_CreateDate", vnpCreateDate);
        vnpParams.put("vnp_ExpireDate", vnpExpireDate);

        String urlParams = getUrlParams(vnpParams);
        String paymentUrl = EnvVariable.VN_PAY_URL + "?" + urlParams;

        // Save payment history
        if (paymentRequestDTO.getVipPack() != 4) {
            saveHistoryPayment(paymentId, orderInfo, totalPrice);
        } else {
            saveHistoryPayment(paymentId, orderInfo, totalAmount);
        }

        return paymentUrl;
    }

    @Override
    public void setVip(String price, User user) {
        switch (price) {
            case VIP:
                user.setCount(user.getCount() + 1);
                user.setTotalCount(user.getTotalCount() + 1);
                break;
            case VIP_PRO:
                user.setCount(user.getCount() + 3);
                user.setTotalCount(user.getTotalCount() + 3);
                break;
            case VIP_SUPER:
                user.setCount(user.getCount() + 5);
                user.setTotalCount(user.getTotalCount() + 5);
                break;
            default:
                user.setCount(0);
        }
        userRepository.save(user);
    }

    @Override
    public HistoryPayment checkBill(Map<String, String[]> params) {
        String paymentId = params.get("vnp_TxnRef")[0];
        String amount = params.get("vnp_Amount")[0];
        String status = params.get("vnp_TransactionStatus")[0];
        String transactionId = params.get("vnp_TransactionNo")[0];

//        UUID paymentId;
//        try {
//            paymentId = UUID.fromString(paymentInfo);
//        } catch (IllegalArgumentException e) {
//            return null;
//        }

        String username = paymentCache.getUsername(paymentId);
        User user = userRepository.findByUsername(username);
        UserDetail userDetail = user.getUserDetail();
        HistoryPayment historyPayment = historyPaymentRepository.findByPaymentId(paymentId);
        String totalPrice = historyPayment.getTotalPrice() + "00";
        String orderInfo = historyPayment.getOderInfo();

        if (paymentId.equals(historyPayment.getPaymentId()) && amount.equals(totalPrice) && status.equals("00")) {
            if (orderInfo.equals(Constant.PAY_PURCHASE_INVOICE)) {
                Cart cart = cartRepository.findCartByUser(user);
                cart.setTotalAmount(0);
                cartLineRepository.deleteCartLineByCart_Id(cart.getId());
                cartRepository.save(cart);
                cart.setCartLineList(new ArrayList<>());
                cartCache.addToCart(user.getId(), cart);
            }
            setVip(amount, user);
            historyPayment.setStatus(true);
            clientService.sendOrderReturn(userDetail.getEmail(), paymentId);
        }

        paymentCache.removePaymentId(username);
        historyPayment.setTransactionId(transactionId);
        historyPaymentRepository.save(historyPayment);
        return historyPayment;
    }

    private void saveHistoryPayment(String uuid, String orderInfo, String totalPrice) {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String time = format.format(cld.getTime());
        HistoryPayment historyPayment = new HistoryPayment();
        historyPayment.setPaymentId(uuid);
        historyPayment.setUser(userRepository.findByUsername(userService.getCurrentUsername()));
        historyPayment.setStatus(false);
        historyPayment.setOderInfo(orderInfo);
        historyPayment.setTotalPrice(totalPrice);
        historyPayment.setPaymentTime(time);
        historyPaymentRepository.save(historyPayment);
    }

    private String getUrlParams(Map<String, String> vnpParams) {
        StringBuilder query = new StringBuilder();
        List fieldNames = new ArrayList(vnpParams.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnpParams.get(fieldName);
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
        return queryUrl;
    }

    private String getClientIP(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        String unknownIP = "unknown";

        if (clientIP == null || clientIP.isEmpty() || unknownIP.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || unknownIP.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || unknownIP.equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }

        return clientIP;
    }
}
