package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> findAll();
    Enrollment findByID(Integer id);
    int insert(Enrollment ment);
    int delete(Integer id);
    int update(Enrollment ment);
    Enrollment search(Integer id);

    int updateList(Enrollment ment);
    int deleteList(Integer id);
    List<Enrollment> findPageHelper(SearchPage searchPage);
    int insertList(Enrollment ment);
}
