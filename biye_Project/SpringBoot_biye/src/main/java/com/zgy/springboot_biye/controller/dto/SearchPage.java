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
    private String username;
    private String phone;
    private String email;
}
