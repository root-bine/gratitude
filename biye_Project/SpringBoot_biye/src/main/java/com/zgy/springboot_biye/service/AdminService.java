package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.domain.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();
    Admin findByID(String studentid);
    Admin findAdmin(String studentid, String password);
    //Admin findById(Integer id);
    int register(Admin admin);
    int updatePass(Admin admin);
}
