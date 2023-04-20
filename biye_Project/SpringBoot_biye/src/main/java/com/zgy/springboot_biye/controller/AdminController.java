package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.Admin;
import com.zgy.springboot_biye.domain.User;
import com.zgy.springboot_biye.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/root")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public Result userList(){
        List<Admin> all = adminService.findAll();
        return Result.success(all);
    }

    // 登录
    @RequestMapping(value = "/in",method = RequestMethod.POST)
    public Result findUser(@RequestBody Admin admin){
        Admin result = adminService.findAdmin(admin.getStudentid(), admin.getPassword());
        return Result.success(result);
    }

    // 修改密码
    @RequestMapping(value = "/set", method = RequestMethod.PUT)
    public  Result newPass(@RequestBody Admin admin){
        List<Admin> all = adminService.findAll();
        for (int i = 0; i < all.size(); i++) {
            Admin result = all.get(i);
            if(result.getStudentid().equals(admin.getStudentid())) {
                return Result.success(adminService.updatePass(admin));
            }
        }
        return Result.error("该用户不存在");
    }
}
