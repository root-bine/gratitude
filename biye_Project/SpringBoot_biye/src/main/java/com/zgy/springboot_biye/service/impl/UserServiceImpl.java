package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.UserDao;
import com.zgy.springboot_biye.domain.User;
import com.zgy.springboot_biye.service.UserService;
import com.zgy.springboot_biye.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int updateOne(User user) {
        return userDao.updateOne(user);
    }

    @Override
    public User findOneById(Integer id) {
        return userDao.findOneByID(id);
    }

    @Override
    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    /*// 分页查询1.0
    @Override
    public List<User> findPage(Integer pageNum, Integer pageSize) {
        return userDao.findPage(pageNum, pageSize);
    }
    @Override
    public long count() {
        return userDao.count();
    }*/

    /*// 分页查询2.0
    @Override
    public List<User> findPage(SearchPage searchPage) {
        return userDao.findPage(searchPage);
    }
    @Override
    public long count(SearchPage searchPage) {
        return userDao.count(searchPage);
    }*/

    // 分页查询3.0
    @Override
    public List<User> findPageHelper(SearchPage searchPage) {
        return userDao.findPageHelper(searchPage);
    }

    // 用户注册
    @Override
    public int register(User user) {
        return userDao.register(user);
    }
    //修改密码
    @Override
    public int updatePass(User user) {
        return userDao.updatePass(user);
    }
}
