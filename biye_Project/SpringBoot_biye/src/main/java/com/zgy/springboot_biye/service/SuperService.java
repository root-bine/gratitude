package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.domain.Super;

import java.util.List;

public interface SuperService {
    Super findAdmin(String studentid, String password);
    int updatePass(Super su);
    List<Super> findAll();
}
