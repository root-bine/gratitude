package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.domain.User;
import com.zgy.springboot_biye.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public Result findAll() {
        return Result.success(applicationService.findAll());
    }

    @PostMapping
    public Result applyOfOne(@RequestBody Application app){
        if(app.getStuID() == null) {
            return Result.error("学号错误！！！");
        }
        List<Application> all = applicationService.findAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).equals(app.getStuID())) {
                return Result.error("学号重复！！！");
            }
        }
        return Result.success(applicationService.insert(app));
    }

    @GetMapping("/{id}")
    public Result findByID(@PathVariable Integer id) {
        return Result.success(applicationService.findByID(id));
    }

    @PutMapping
    public Result update (@RequestBody Application app){
        return Result.success(applicationService.update(app));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(applicationService.delete(id));
    }

    @GetMapping("/searching/{id}")
    public Result search(@PathVariable Integer id) {
        Application app = applicationService.search(id);
        return Result.success(app);
    }

    @GetMapping("/page")
    //currentPage: 当前页码  pageSize: 每页个数
    public Result findByPage(SearchPage searchPage){
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误！！！");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Application> page = applicationService.findPageHelper(searchPage);
        return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
    }

    @PutMapping("/list")
    public Result updateList(@RequestBody Application app) {
        return Result.success(applicationService.updateList(app));
    }

    @DeleteMapping("/list/{id}")
    public Result deleteList(@PathVariable Integer id){
        if(id == null || id == 0){
            return Result.error("参数错误");
        }
        /*返回数据库操作记录数, > 0: 成功, < 0: 失败*/
        return Result.success(applicationService.deleteList(id) == 1);
    }
}
