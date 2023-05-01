package com.zgy.springboot_biye.controller;

import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.Send;
import com.zgy.springboot_biye.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class SendMailController {
    @Autowired
    private SendMailService sendMailService;

    @PostMapping
    public Result QQsend(@RequestBody Send send) {
        sendMailService.sendMail(send);
        return Result.success("发送成功");
    }
}
