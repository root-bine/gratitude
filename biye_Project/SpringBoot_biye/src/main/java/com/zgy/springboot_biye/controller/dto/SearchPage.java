package com.zgy.springboot_biye.controller.dto;

import lombok.Data;

/**
 * 用于实现前后端的分页多条件查询
 * 存放相关参数
 */
@Data
public class SearchPage {
    private Integer currentPage;
    private Integer pageNum;
    private Integer pageSize;
    // user表
    private String username;
    private String department;
    private String studentid;
    //申请表 报名表
    private String stuName;
    private String stuID;
    private String profession;
}
