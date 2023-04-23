package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
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
    Enrollment search(Integer id);

    /**
     * 分页面板相关功能
     * @return
     */
    // 编辑
    int updateList(Enrollment ment);
    // 删除
    int deleteList(Integer id);
    // 分页 (筛选展示报名提交的信息)
    List<Enrollment> findPageHelper(SearchPage searchPage);

    // 导出
    int insertList(Enrollment ment);
}
