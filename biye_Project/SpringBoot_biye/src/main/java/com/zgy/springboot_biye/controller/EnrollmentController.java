package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Application;
import com.zgy.springboot_biye.domain.Enrollment;
import com.zgy.springboot_biye.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add")
public class EnrollmentController {
    @Autowired
    private EnrollmentService mentService;

    @PostMapping
    public Result insert(@RequestBody Enrollment ment){
        if(ment.getStuID() == null) {
            return Result.error("学号错误！！！");
        }
        List<Enrollment> all = mentService.findAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).equals(ment.getStuID())) {
                return Result.error("学号重复！！！");
            }
        }
        return Result.success(mentService.insert(ment));
    }
    @GetMapping("/{id}")
    public Result findByID(@PathVariable Integer id) {
        return Result.success(mentService.findByID(id));
    }

    @PutMapping
    public Result update (@RequestBody Enrollment ment) {
        return Result.success(mentService.update(ment));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(mentService.delete(id));
    }

    @GetMapping("/search/{id}")
    public Result search(@PathVariable Integer id) {
        Enrollment ment = mentService.search(id);
        return Result.success(ment);
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
        List<Enrollment> page = mentService.findPageHelper(searchPage);
        return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
    }

    @PutMapping("/list")
    public Result updateList(@RequestBody Enrollment ment) {
        return Result.success(mentService.updateList(ment));
    }

    @DeleteMapping("/list/{id}")
    public Result deleteList(@PathVariable Integer id){
        if(id == null || id == 0){
            return Result.error("参数错误");
        }
        /*返回数据库操作记录数, > 0: 成功, < 0: 失败*/
        return Result.success(mentService.deleteList(id) == 1);
    }
    @PostMapping("/out")
    public Result insertByFin(@RequestBody Enrollment ment) {
        return Result.success(mentService.insertList(ment));
    }
}
