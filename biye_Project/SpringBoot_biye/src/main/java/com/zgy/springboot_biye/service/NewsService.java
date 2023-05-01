package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.News;

import java.util.List;

public interface NewsService {
    List<News> findPageHelper(SearchPage searchPage);
    int insert(News news);
    int update(News news);
    int delete(Integer id);
}
