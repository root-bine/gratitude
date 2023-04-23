package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Admission;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission")
public class AdmissionController {
    @Autowired
    private AdmissionService service;

    @GetMapping("/page")
    //currentPage: 当前页码  pageSize: 每页个数
    public Result findByPage(SearchPage searchPage){
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误！！！");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Admission> page = service.findPageHelpers(searchPage);
        return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(service.delete(id));
    }
}
