package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao {
    List<User> findAll();
    User findById(Integer id);
    User findUser(String username, String password);
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
