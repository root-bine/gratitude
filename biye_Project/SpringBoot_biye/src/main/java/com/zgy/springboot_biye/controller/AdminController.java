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

    // 新增 or 保存
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        return Result.success(adminService.insert(admin));
    }

    // 修改 or 更新
    @PutMapping
    public Result update(@RequestBody Admin admin) {
        return Result.success(adminService.update(admin));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(adminService.delete(id));
    }

    // 分页 + 查询
    @GetMapping("/page")
    public Result findPage(SearchPage searchPage) {
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage < 1 || pageSize == null || pageSize < 1) {
            return Result.error("参数错误");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Admin> page = adminService.findPageHelper(searchPage);
        return Result.success(new PageInfo<>(page));
    }
}
