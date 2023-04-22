package com.zgy.springboot_biye.controller.dto;

import lombok.Data;

@Data
public class Send {
    private String from;
    private String to;
    private String subject;
    private String content;
}
