package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.dao.ApplicationDao;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public int insert(Application application) {
        return applicationDao.insert(application);
    }

    @Override
    public List<Application> findAll() {
        return applicationDao.findAll();
    }

    @Override
    public Application findByID(Integer id) {
        return applicationDao.findByID(id);
    }

    @Override
    public int update(Application application) {
        return applicationDao.update(application);
    }

    @Override
    public int delete(Integer id) {
        return applicationDao.delete(id);
    }

    @Override
    public Application search(Application app) {
        return applicationDao.search(app);
    }
}
