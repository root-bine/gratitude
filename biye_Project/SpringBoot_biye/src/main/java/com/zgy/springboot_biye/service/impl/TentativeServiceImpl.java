package com.zgy.springboot_biye.service.impl;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.TentativeDao;
import com.zgy.springboot_biye.domain.Tentative;
import com.zgy.springboot_biye.service.TentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TentativeServiceImpl implements TentativeService {
    @Autowired
    private TentativeDao ten;

    @Override
    public int delete(Integer id) {
        return ten.delete(id);
    }

    @Override
    public List<Tentative> findPageHelper(SearchPage searchPage) {
        return ten.findPageHelper(searchPage);
    }

    @Override
    public List<Tentative> findAll() {
        return ten.findAll();
    }
}
