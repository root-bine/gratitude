package com.zgy.springboot_biye.controller;

import com.zgy.springboot_biye.config.Result;
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
}
