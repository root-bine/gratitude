package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 院级表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    private int id;
    private String username;
    private String password;
    private String studentid;
    private String department;
    private String college;
    private String region;
}
