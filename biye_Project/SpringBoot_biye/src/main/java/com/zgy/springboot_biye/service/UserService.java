package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.controller.dto.UserDTO;
import com.zgy.springboot_biye.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    int  updateOne(User user);
    User findOneById(Integer id);
    User findUser(String username, String password);
    int insert(User user);
    int update(User user);
    int deleteById(Integer id);
    /*分页1.0+按条件查询*/
    //List<User> findPage(Integer pageNum, Integer pageSize);
    //long count();
    /*分页2.0+按条件查询*/
    //List<User> findPage(SearchPage searchPage);
    //long count(SearchPage searchPage);
    /*分页3.0+按条件查询*/
    List<User> findPageHelper(SearchPage searchPage);

    int register(User user);

    int updatePass(User user);
}
