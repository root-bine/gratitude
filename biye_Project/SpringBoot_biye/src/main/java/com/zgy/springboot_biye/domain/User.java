package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String college;
    private String department;
    private String username;
    private String password;
    private String studentid;
    private String profession;
    private String region;
    private String email;
    private String sex;
    private Integer age;

}
