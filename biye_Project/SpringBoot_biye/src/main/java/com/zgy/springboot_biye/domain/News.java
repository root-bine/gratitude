package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 公告表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
    private Integer id;
    private String title;
    private String content;
    private Date time;
    private String updater;
}
