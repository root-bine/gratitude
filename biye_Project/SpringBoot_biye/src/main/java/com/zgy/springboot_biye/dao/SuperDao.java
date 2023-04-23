package com.zgy.springboot_biye.dao;

import com.zgy.springboot_biye.domain.Super;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuperDao {
    Super findAdmin(String studentid, String password);
    int updatePass(Super su);
    List<Super> findAll();
}
