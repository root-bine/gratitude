package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationDao {
    // 学生上传报名申请信息
    int insert(Application application);
    // 查询全部信息
    List<Application> findAll();
    // 根据ID查询用户信息
    Application findByID(Integer id);
    // 修改单一用户信息
    int update(Application application);
    //删除单一用户信息
    int delete(Integer id);
    // 根据学号和姓名查询结果
    Application search(Integer id);

    //管理员对申请表进行审核、删除, 实现分页功能
    int updateList(Application app);
    int deleteList(Integer id);
    List<Application> findPageHelper(SearchPage searchPage);
}
