package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.dao.UserDao;
import com.zgy.springboot_biye.domain.User;
import com.zgy.springboot_biye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    /*@Autowired
    private UserDao userDao;*/
    @Autowired
    private UserService userService;


    /** 分页 1.0
     @GetMapping("/page")
    public Result findByPage(@RequestParam Integer currentPage, @RequestParam Integer pageSize){
        if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误！！！");
        }
        Integer pageNum = (currentPage - 1)*pageSize;
        List<User> page = userDao.findPage(pageNum, pageSize);
        long total = userDao.count();

        Map<String, Object> map = new HashMap<>();
        map.put("data",page);
        map.put("total",total);
        return Result.success(map);
    }*/

    /**分页 2.0
     @GetMapping("/page")
     public Result findByPage(SearchPage searchPage){
     Integer currentPage = searchPage.getCurrentPage();
     Integer pageSize = searchPage.getPageSize();
     if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
        return Result.error("参数错误！！！");
     }
     Integer pageNum = (currentPage - 1)*pageSize;
     searchPage.setPageNum(pageNum);
     List<User> page = userDao.findPage(pageNum, pageSize);
     long total = userDao.count(searchPage);

     Map<String, Object> map = new HashMap<>();
     map.put("data",page);
     map.put("total",total);
     return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
     }*/

    // 分页3.0
    @GetMapping("/page")
    //currentPage: 当前页码  pageSize: 每页个数
    public Result findByPage(SearchPage searchPage){
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误！！！");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<User> page = userService.findPageHelper(searchPage);
        return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
    }

    // 登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result findUser(@RequestBody User user){
        if(user.getUsername() == null|| user.getPassword() == null){
            return Result.error("用户名或密码为空");
        }
        List<User> list = userService.findAll();
        User result = userService.findUser(user.getUsername(), user.getPassword());
        if(!list.contains(result)) {
            return Result.error("用户不存在, 请注册");
        }
        return Result.success(result);
    }
    // 注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result registerUser(@RequestBody User user){
        User re1 = userService.findUser(user.getUsername(), user.getPassword());
        if(re1 != null){
            return Result.error("用户已存在！！！");
        }
        int result = userService.register(user);
        if(result == 0){
            // throw new RuntimeException();
            Result.error("用户名或密码为空");
        }
        return Result.success(result);
    }

    // 修改密码
    @RequestMapping(value = "/new", method = RequestMethod.PUT)
    public  Result newPass(@RequestBody User user){
        /*Map<String, String> map = new HashMap<>();
        List<User> all = userDao.findAll();
        map.put(user.getUsername(),user.getPassword());
        for (int i = 0; i < all.size(); i++) {
            if(map.containsKey(all.get(i).getUsername())) {
                return Result.error("该用户不存在");
            }
            break;
        }*/
        List<User> all = userService.findAll();
        for (int i = 0; i < all.size(); i++) {
            User result = all.get(i);
            if(result.getUsername().equals(user.getUsername())) {
                return Result.success(userService.updatePass(user));
            }
        }
        return Result.error("该用户不存在");
    }

    @GetMapping("/{id}") // https://localhost:8090/users/id
    public Result findById(@PathVariable Integer id){
        return Result.success(userService.findById(id));
    }

    // 查询全部
    @GetMapping
    public Result findAll(){
        return Result.success(userService.findAll());
    }

    // 新增 or 保存
    @PostMapping
    public Result save(@RequestBody User user){
        return Result.success(userService.insert(user));
    }

    // 修改 or 更新
    @PutMapping
    public Result update(@RequestBody User user){
        return Result.success(userService.update(user));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if(id == null || id == 0){
            return Result.error("参数错误");
        }
        /*返回数据库操作记录数, > 0: 成功, < 0: 失败*/
        return Result.success(userService.deleteById(id) == 1);
    }
}