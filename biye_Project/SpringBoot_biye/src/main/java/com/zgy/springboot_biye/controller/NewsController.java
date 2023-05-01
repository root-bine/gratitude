package com.zgy.springboot_biye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgy.springboot_biye.config.DateUtil;
import com.zgy.springboot_biye.config.Result;
import com.zgy.springboot_biye.controller.dto.SearchPage;
import com.zgy.springboot_biye.domain.News;
import com.zgy.springboot_biye.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    // 分页
    @GetMapping("/page")
    public Result findByPage(SearchPage searchPage){
        Integer currentPage = searchPage.getCurrentPage();
        Integer pageSize = searchPage.getPageSize();
        if(currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误！！！");
        }
        PageHelper.startPage(currentPage, pageSize);
        List<News> page = newsService.findPageHelper(searchPage);
        return Result.success(new PageInfo<>(page)); //PageInfo就是一个分页Bean
    }
    // 新增 or 保存
    @PostMapping
    public Result save(@RequestBody News news){
        return Result.success(newsService.insert(news));
    }

    // 修改 or 更新
    @PutMapping
    public Result update(@RequestBody News news){
        return Result.success(newsService.update(news));
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if(id == null || id == 0){
            return Result.error("参数错误");
        }
        /*返回数据库操作记录数, > 0: 成功, < 0: 失败*/
        return Result.success(newsService.delete(id) == 1);
    }
}
