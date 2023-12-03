package com.codegym.aurora.service.impl;

import com.codegym.aurora.payload.response.BillResponseDTO;
import com.codegym.aurora.payload.response.DataMailDTO;
import com.codegym.aurora.payload.sdi.ClientSdi;
import com.codegym.aurora.service.ClientService;
import com.codegym.aurora.service.HistoryPaymentService;
import com.codegym.aurora.service.MailService;
import com.codegym.aurora.service.UserService;
import com.codegym.aurora.util.MailConstant;
import com.codegym.aurora.util.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final MailService mailService;

    private final UserService userService;

    private final HistoryPaymentService historyPaymentService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();
            String email = sdi.getEmail();
            dataMail.setTo(email);
            dataMail.setSubject(MailConstant.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            String tempPassword = DataUtils.generateTempPwd(6);
            userService.updateUserPassword(email,tempPassword);
            props.put("password", tempPassword);
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, MailConstant.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean sendOrderReturn(String email, String paymentId) {
        try {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(email);
            dataMail.setSubject(MailConstant.SEND_MAIL_SUBJECT.CLIENT_PAYMENT);

            Map<String, Object> props = new HashMap<>();
            BillResponseDTO bill = historyPaymentService.getBill(paymentId);
            props.put("fullName", bill.getFullName());
            props.put("paymentId", bill.getPaymentId());
            props.put("orderInfo", bill.getOrderInfo());
            props.put("paymentTime", bill.getPaymentTime());
            props.put("totalPrice", bill.getTotalPrice());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, MailConstant.TEMPLATE_FILE_NAME.CLIENT_PAYMENT);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }


}


