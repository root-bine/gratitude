package com.zgy.springboot_biye.service;

import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Tentative;
import java.util.List;

public interface TentativeService {
    int delete(Integer id);
    List<Tentative> findPageHelper(SearchPage searchPage);
    List<Tentative> findAll();
}
