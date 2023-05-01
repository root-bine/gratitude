package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Tentative;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TentativeDao {
    // 删除
    int delete(Integer id);
    // 分页+查询
    List<Tentative> findPageHelper(SearchPage searchPage);
    // 数据导出
    List<Tentative> findAll();
}
