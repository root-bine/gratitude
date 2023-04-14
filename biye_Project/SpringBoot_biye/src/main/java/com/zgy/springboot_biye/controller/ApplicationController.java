package com.zgy.springboot_biye.controller;

import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.domain.Application;
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
}
