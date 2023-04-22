package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.Send;
import com.zgy.springboot_biye.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.mailUsername}")
    private String on;

    @Override
    public void sendMail(Send send) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(send.getTo());
        message.setSubject(send.getSubject());
        message.setText(send.getContent());
        javaMailSender.send(message);
    }

    @Override
    public void sendMails(Send send) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(on);
        message.setTo(send.getTo());
        message.setSubject(send.getSubject());
        message.setText(send.getContent());
        javaMailSender.send(message);
    }
}
