package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();
    Admin findAdmin(String studentid, String password);
    int updatePass(Admin admin);
    // 新增
    int insert(Admin admin);

    // 编辑
    int update(Admin admin);

    // 删除
    int delete(Integer id);

    // 分页+查询
    List<Admin> findPageHelper(SearchPage searchPage);
}
