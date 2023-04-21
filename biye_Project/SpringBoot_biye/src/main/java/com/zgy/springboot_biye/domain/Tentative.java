package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 暂拟名单
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tentative {
    private int id;
    private String stuID;
    private String stuName;
    private String profession;
}
