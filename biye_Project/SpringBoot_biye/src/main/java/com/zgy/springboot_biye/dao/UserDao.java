package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao {
    List<User> findAll();
    // 根据ID查询用户名, 用于展示在前端右上角
    User findById(Integer id);
    // 根据ID查询用户部分信息, 显示个人单独部分信息
    User findOneByID(Integer id);
    int updateOne(User user);

    // 登录
    User findUser(String studentid, String password);
    int insert(User user);
    int update(User user);
    int deleteById(Integer id);

    /*分页1.0+按条件查询*/
    List<User> findPage(Integer pageNum, Integer pageSize);
    long count();
    /*分页2.0+按条件查询*/
    List<User> findPage(SearchPage searchPage);
    long count(SearchPage searchPage);

    /*分页3.0+按条件查询*/
    List<User> findPageHelper(SearchPage searchPage);
    // 注册
    int register(User user);
    // 修改密码
    int updatePass(User user);
}
