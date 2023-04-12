<template>
  <el-menu  class="el" router>
    <el-menu-item index="adminF">
      <el-icon><HomeFilled /></el-icon>
      <span style="font-size: 25px">首页</span>
    </el-menu-item>

    <el-sub-menu index="1">
      <template #title>
        <el-icon><Avatar /></el-icon>
        <span style="font-size: 25px">个人中心</span>
      </template>
      <el-menu-item class="els" index="person">个人信息</el-menu-item>
      <el-menu-item class="els" index="new">修改密码</el-menu-item>
    </el-sub-menu>

    <el-menu-item index="home">
      <el-icon><Grid /></el-icon>
      <span style="font-size: 25px">推免申请</span>
    </el-menu-item>

    <el-sub-menu index="3">
      <template #title>
        <el-icon><icon-menu /></el-icon>
        <span style="font-size: 25px">推免报名</span>
      </template>
      <el-menu-item class="els" index="application">推免申请表</el-menu-item>
      <el-menu-item class="els" index="console">综合信息表</el-menu-item>
    </el-sub-menu>

    <el-menu-item index="4">
      <el-icon><HelpFilled /></el-icon>
      <span style="font-size: 25px">申请结果</span>
    </el-menu-item>

    <el-menu-item index="5">
      <el-icon><Promotion /></el-icon>
      <span style="font-size: 25px">录取结果</span>
    </el-menu-item>

    <el-menu-item index="6">
      <el-icon><Message /></el-icon>
      <span style="font-size: 25px">留言板</span>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import {HomeFilled, Avatar,Message, Menu as IconMenu, Grid, HelpFilled, Promotion} from '@element-plus/icons-vue'
</script>

<style scoped>
  .el {
    background-color: cadetblue;
    height:680px;
    color: burlywood;
  }
  .els {
    background-color: bisque;
    font-size: 20px;
    color: chocolate;
  }
</style>