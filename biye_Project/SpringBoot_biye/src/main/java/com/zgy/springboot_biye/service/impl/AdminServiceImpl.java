package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.AdminDao;
import com.zgy.springboot_biye.domain.Admin;
import com.zgy.springboot_biye.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin findAdmin(String studentid, String password) {
        return adminDao.findAdmin(studentid, password);
    }

    @Override
    public int updatePass(Admin admin) {
        return adminDao.updatePass(admin);
    }

    @Override
    public int insert(Admin admin) {
        return adminDao.insert(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminDao.update(admin);
    }

    @Override
    public int delete(Integer id) {
        return adminDao.delete(id);
    }

    @Override
    public List<Admin> findPageHelper(SearchPage searchPage) {
        return adminDao.findPageHelper(searchPage);
    }
}
