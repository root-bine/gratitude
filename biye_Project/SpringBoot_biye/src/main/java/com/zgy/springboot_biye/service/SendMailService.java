package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.Send;

public interface SendMailService {
    /**
     * [院级\校级]管理员邮件通知学生
     * @param send
     */
    void sendMail(Send send);
}
