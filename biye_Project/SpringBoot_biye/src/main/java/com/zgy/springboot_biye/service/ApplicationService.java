package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.domain.Application;

import java.util.List;

public interface ApplicationService {
    int insert(Application application);
    List<Application> findAll();
    Application findByID(Integer id);
    int update(Application application);
    int delete(Integer id);
    Application search(Integer id);
}
