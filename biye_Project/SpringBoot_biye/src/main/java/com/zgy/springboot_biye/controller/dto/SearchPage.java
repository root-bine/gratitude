package com.zgy.springboot_biye.controller.dto;

import lombok.Data;

/**
 * 用于实现前后端的分页多条件查询
 * 存放相关参数
 */
@Data
public class SearchPage {
    // 分页参数
    private Integer currentPage;
    private Integer pageNum;
    private Integer pageSize;
    // 用户表、管理员表
    private String username;
    private String department;
    private String studentid;
    // 申请表、报名表、拟定名单
    private String stuName;
    private String stuID;
    private String profession;
    // 公告表
    private String title;
    private String updater;
}
