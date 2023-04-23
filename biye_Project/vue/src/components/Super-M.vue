<template>
  <el-menu  class="el" router>
    <el-menu-item index="superPage">
      <el-icon><HomeFilled /></el-icon>
      <span style="font-size: 25px">首页</span>
    </el-menu-item>

    <el-menu-item index="userMessage">
      <el-icon><Grid /></el-icon>
      <span style="font-size: 25px">学生列表</span>
    </el-menu-item>

    <el-menu-item index="reviewForExemption">
      <el-icon><Finished /></el-icon>
      <span style="font-size: 25px">推免审核</span>
    </el-menu-item>

    <el-menu-item index="Email">
      <el-icon><Comment /></el-icon>
      <span style="font-size: 25px">邮件通知</span>
    </el-menu-item>
    <el-menu-item index="admissionList">
      <el-icon><Promotion /></el-icon>
      <span style="font-size: 25px">录取结果</span>
    </el-menu-item>

    <el-menu-item index="adminManagement">
      <el-icon><UserFilled /></el-icon>
      <span style="font-size: 25px">用户管理</span>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import {HomeFilled, Finished, Grid, Comment, Promotion, UserFilled} from '@element-plus/icons-vue'
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