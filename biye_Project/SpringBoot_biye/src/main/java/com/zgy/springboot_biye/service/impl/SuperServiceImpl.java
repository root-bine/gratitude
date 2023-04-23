package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.dao.SuperDao;
import com.zgy.springboot_biye.domain.Super;
import com.zgy.springboot_biye.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuperServiceImpl implements SuperService {
    @Autowired
    private SuperDao superDao;

    @Override
    public Super findAdmin(String studentid, String password) {
        return superDao.findAdmin(studentid, password);
    }

    @Override
    public int updatePass(Super su) {
        return superDao.updatePass(su);
    }

    @Override
    public List<Super> findAll() {
        return superDao.findAll();
    }
}
