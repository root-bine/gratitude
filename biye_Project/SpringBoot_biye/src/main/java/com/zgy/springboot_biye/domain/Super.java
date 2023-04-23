package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 校级表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Super {
    private int id;
    private String studentid;
    private String username;
    private String password;
    private String college;
}
