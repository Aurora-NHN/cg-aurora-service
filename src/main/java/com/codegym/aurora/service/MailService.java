package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.DataMailDTO;
import javax.mail.MessagingException;


public interface MailService {

    void sendHtmlMail (DataMailDTO dataMailDto, String templateName) throws MessagingException;
}
