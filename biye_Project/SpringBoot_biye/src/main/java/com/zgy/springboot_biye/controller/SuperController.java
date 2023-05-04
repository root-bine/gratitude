package com.zgy.springboot_biye.controller;

import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.domain.Super;
import com.zgy.springboot_biye.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/super")
public class SuperController {
    @Autowired
    private SuperService service;

    // 登录
    @RequestMapping(value = "/inner",method = RequestMethod.POST)
    public Result findUser(@RequestBody Super su){
        if(su.getStudentid() == null || su.getPassword() == null) {
            return Result.error("用户名或者密码错误！！！");
        }
        List<Super> all = service.findAll();
        Super result = service.findAdmin(su.getStudentid(), su.getPassword());
        if(!all.contains(result)) {
            return Result.error("用户不存在！！！");
        }
        return Result.success(result);
    }

    // 修改密码
    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public  Result newPass(@RequestBody Super su){
        List<Super> all = service.findAll();
        for (int i = 0; i < all.size(); i++) {
            Super result = all.get(i);
            if(result.getStudentid().equals(su.getStudentid())) {
                return Result.success(service.updatePass(su));
            }
        }
        return Result.error("该用户不存在");
    }
}
