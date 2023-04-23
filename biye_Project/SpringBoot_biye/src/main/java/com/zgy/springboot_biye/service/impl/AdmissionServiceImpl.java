package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.AdmissionDao;
import com.zgy.springboot_biye.domain.Admission;
import com.zgy.springboot_biye.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {
    @Autowired
    private AdmissionDao admissionDao;

    @Override
    public List<Admission> findPageHelpers(SearchPage searchPage) {
        return admissionDao.findPageHelpers(searchPage);
    }

    @Override
    public int delete(Integer id) {
        return admissionDao.delete(id);
    }
}
