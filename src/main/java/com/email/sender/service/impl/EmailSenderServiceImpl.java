package com.email.sender.service.impl;

import com.email.sender.type.EmailType;
import com.email.sender.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    @Override
    public void sendEmail(EmailType context) {
        try {
            Email email = new SimpleEmail();
            email.addTo(context.getAddress());
            email.setSubject("Greeting from Baran");

            email.setMsg("Hi there, How are you" + context.getName() + " " + context.getSurname());
            email.setHostName("testmail.com");
            email.setFrom("baran.ipek@gmail.com");

            email.send();
            log.info("Sent email to user " + context.getName() + " " + context.getSurname());

        } catch (EmailException e) {
            log.error("Error sending email while sending user" + context.getAddress());
        }
    }
}
