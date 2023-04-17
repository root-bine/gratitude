package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.domain.Enrollment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollmentDao {
    List<Enrollment> findAll();
    Enrollment findByID(Integer id);
    int insert(Enrollment ment);
    int delete(Integer id);
    int update(Enrollment ment);

    // 根据学号和姓名查询结果
    Enrollment search(Enrollment ment);
}
