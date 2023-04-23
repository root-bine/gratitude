package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admission {
    private int id;
    private String stuID;
    private String stuName;
    private String profession;
}
