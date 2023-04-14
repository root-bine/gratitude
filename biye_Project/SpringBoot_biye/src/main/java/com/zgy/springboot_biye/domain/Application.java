package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application {
    private int id;
    private String stuID;
    private String stuName;
    private String profession;
    private double test;
    private double average;
    private String course;
    private String file;
    private String myself;
    private String school;
}
