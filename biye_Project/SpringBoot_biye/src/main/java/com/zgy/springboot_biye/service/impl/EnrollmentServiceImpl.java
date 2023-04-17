package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.dao.EnrollmentDao;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.domain.Enrollment;
import com.zgy.springboot_biye.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentDao mentDao;


    @Override
    public List<Enrollment> findAll() {
        return mentDao.findAll();
    }

    @Override
    public Enrollment findByID(Integer id) {
        return mentDao.findByID(id);
    }

    @Override
    public int insert(Enrollment ment) {
        return mentDao.insert(ment);
    }

    @Override
    public int delete(Integer id) {
        return mentDao.delete(id);
    }

    @Override
    public int update(Enrollment ment) {
        return mentDao.update(ment);
    }

    @Override
    public Enrollment search(Enrollment ment) {
        return mentDao.search(ment);
    }
}
