package com.email.sender.service;


import com.email.sender.model.type.EmailType;
import org.apache.commons.mail.EmailException;

public interface EmailSenderService {
    void sendEmail (EmailType context) throws EmailException;
}
