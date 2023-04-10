package com.zgy.springboot_biye.controller;

import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.domain.Admin;
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
        if(admin.getUsername() == null|| admin.getPassword() == null){
            return Result.error("用户名或密码为空");
        }
        List<Admin> list = adminService.findAll();
        Admin result = adminService.findAdmin(admin.getUsername(), admin.getPassword());
        if(!list.contains(result)) {
            return Result.error("用户不存在, 请注册");
        }
        return Result.success(result);
    }
    // 注册
    @RequestMapping(value = "/ano",method = RequestMethod.POST)
    public Result registerUser(@RequestBody Admin admin){
        Admin re1 = adminService.findAdmin(admin.getUsername(), admin.getPassword());
        if(re1 != null){
            return Result.error("用户已存在！！！");
        }
        int result = adminService.register(admin);
        if(result == 0){
            // throw new RuntimeException();
            Result.error("用户名或密码为空");
        }
        return Result.success(result);
    }
    // 修改密码
    @RequestMapping(value = "/set", method = RequestMethod.PUT)
    public  Result newPass(@RequestBody Admin admin){
        List<Admin> all = adminService.findAll();
        for (int i = 0; i < all.size(); i++) {
            Admin result = all.get(i);
            if(result.getUsername().equals(admin.getUsername())) {
                return Result.success(adminService.updatePass(admin));
            }
        }
        return Result.error("该用户不存在");
    }
}
