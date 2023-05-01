package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NewsDao {
    List<News> findPageHelper(SearchPage searchPage);
    int insert(News news);
    int update(News news);
    int delete(Integer id);
}
