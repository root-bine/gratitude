package com.zgy.springboot_biye.dao;


import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDao {
    // 查找所有用户
    List<Admin> findAll();
    // 登录
    Admin findAdmin(String studentid, String password);
    // 修改当前用户密码
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
