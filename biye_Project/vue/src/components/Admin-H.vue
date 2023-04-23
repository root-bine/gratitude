<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <img src="/images/img.png" class="logo">
    </el-col>
    <el-col :span="16" class="clo1">
      后台信息管理
    </el-col>
    <el-col :span="4">
      <!--<span class="demonstration">用户</span>-->
      <el-dropdown trigger="click">
          <span class="el-dropdown-link">
            管理员: 院级
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item :icon="CirclePlus" style="font-size: 20px">系统公告</el-dropdown-item>
            <el-dropdown-item :icon="EditPen" style="font-size: 20px" @click="$router.push({name: 'NewPassword'})">修改密码</el-dropdown-item>
            <el-dropdown-item :icon="CircleCheck" style="font-size: 20px" @click="$router.push('/login')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script setup>
  import {ArrowDown, EditPen, CircleCheck, CirclePlus} from '@element-plus/icons-vue'
</script>

<style scoped>
.logo {
  width: 75px;
  height: 65px;
  text-align: left;
  margin-top: 10px;
}
.clo1 {
  color: aquamarine;
  margin-bottom: 20px;
  margin-top: 10px;
  font-size: 35px;
  text-align: center;
}
.el-dropdown-link {
  display: block;
  font-size: 25px;
  margin-bottom: 20px;
  margin-top: 10px;
  color: gold;
}
</style>