package com.zgy.springboot_biye.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String studentid;
    private String college;
    private String profession;
    private String region;
    private String email;
    private String avaterUrl;
    private String sex;

    private String address;
    private Integer age;
    private String phone;
}
