<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <img src="/images/img.png" class="logo">
    </el-col>
    <el-col :span="16" class="clo1">
      推免研究生评选管理系统
    </el-col>
    <el-col :span="4">
      <!--<span class="demonstration">用户</span>-->
      <el-dropdown trigger="click">
          <span class="el-dropdown-link">
            学生: {{user.username}}
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item :icon="CirclePlus" style="font-size: 20px" @click="$router.push({name: 'NewsUser'})">系统公告</el-dropdown-item>
            <el-dropdown-item :icon="Edit" style="font-size: 20px" @click="$router.push({name: 'EditPerson'})">编辑信息</el-dropdown-item>
            <el-dropdown-item :icon="CircleCheck" style="font-size: 20px" @click="$router.push('/login')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script setup>
  import {ArrowDown, CircleCheck, CirclePlus, Edit} from '@element-plus/icons-vue'
  import request from "../utils/request.js";
  import {reactive, ref} from "vue";
  import {useRouter} from 'vue-router'
  const router = useRouter()
  const id = router.currentRoute.value.params.id
  const user = reactive({})
  request.get('/users/'+id).then(res => {
     user.username = res.data.username
  })
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
  color: coral;
}
</style>