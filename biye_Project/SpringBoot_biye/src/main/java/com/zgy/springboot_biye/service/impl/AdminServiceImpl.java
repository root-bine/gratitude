package com.zgy.springboot_biye.service.impl;

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
    public Admin findAdmin(String username, String password) {
        return adminDao.findAdmin(username, password);
    }

    @Override
    public int register(Admin admin) {
        return adminDao.register(admin);
    }

    @Override
    public int updatePass(Admin admin) {
        return adminDao.updatePass(admin);
    }
}
