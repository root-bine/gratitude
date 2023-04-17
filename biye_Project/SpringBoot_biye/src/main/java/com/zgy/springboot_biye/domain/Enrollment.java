package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报名表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Enrollment {
    private int id;
    private String stuID;
    private String stuName;
    private String sex;
    private String profession;
    private String english;
    private double average;
    private String gratitude;
    private String stuStatus;
    private String phone;
    private String myself;
    private String file;
    private String audit;
}
