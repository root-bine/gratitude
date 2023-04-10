package com.zgy.springboot_biye.dao;


import com.zgy.springboot_biye.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDao {
    List<Admin> findAll();

    Admin findAdmin(String username, String password);
    //Admin findById(Integer id);
    int register(Admin admin);
    int updatePass(Admin admin);
}
