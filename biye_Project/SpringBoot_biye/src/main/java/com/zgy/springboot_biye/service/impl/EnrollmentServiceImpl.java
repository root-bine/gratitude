package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.EnrollmentDao;
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
    public Enrollment search(Integer id) {
        return mentDao.search(id);
    }

    @Override
    public int updateList(Enrollment ment) {
        return mentDao.updateList(ment);
    }

    @Override
    public int deleteList(Integer id) {
        return mentDao.deleteList(id);
    }

    @Override
    public List<Enrollment> findPageHelper(SearchPage searchPage) {
        return mentDao.findPageHelper(searchPage);
    }

    @Override
    public int insertList(Enrollment ment) {
        return mentDao.insertList(ment);
    }
}
