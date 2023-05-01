package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Admission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmissionDao {
    // 分页 (录取结果)
    List<Admission> findPageHelpers(SearchPage searchPage);

    int delete(Integer id);

    // 导出Excel
    List<Admission> findAll();
}
