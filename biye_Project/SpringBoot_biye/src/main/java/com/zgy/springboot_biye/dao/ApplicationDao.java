package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.domain.Application;
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

    int delete(Integer id);
}
