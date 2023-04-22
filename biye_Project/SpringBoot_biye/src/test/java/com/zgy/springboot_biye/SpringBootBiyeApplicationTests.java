package com.zgy.springboot_biye;

import com.zgy.springboot_biye.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBiyeApplicationTests {
    @Autowired
    private SendMailService sendMailService;

    @Test
    void contextLoads() {
    }
}
