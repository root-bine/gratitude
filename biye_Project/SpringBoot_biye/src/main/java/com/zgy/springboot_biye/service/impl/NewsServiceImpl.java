package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.config.DateUtil;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.NewsDao;
import com.zgy.springboot_biye.domain.News;
import com.zgy.springboot_biye.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    private Date now = DateUtil.time();

    @Override
    public List<News> findPageHelper(SearchPage searchPage) {
        return newsDao.findPageHelper(searchPage);
    }

    @Override
    public int insert(News news) {
        if(news.getId() == null) {
            news.setTime(now);
        }
        return newsDao.insert(news);
    }

    @Override
    public int update(News news) {
        if(news.getId() != null) {
            news.setTime(now);
        }
        return newsDao.update(news);
    }

    @Override
    public int delete(Integer id) {
        return newsDao.delete(id);
    }
}
