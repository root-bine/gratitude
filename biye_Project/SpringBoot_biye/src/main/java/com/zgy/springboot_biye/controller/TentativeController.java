package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Tentative;
import com.zgy.springboot_biye.service.TentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ten")
public class TentativeController {
    @Autowired
    private TentativeService ten;

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(ten.delete(id));
    }

    @GetMapping("/page")
    public Result findPage(SearchPage searchPage) {
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage < 1 || pageSize == null || pageSize <1) {
            return Result.error("参数错误");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Tentative> page = ten.findPageHelper(searchPage);
        PageInfo<Tentative> pageInfo = new PageInfo<>(page);
        return Result.success(pageInfo);
    }
}
