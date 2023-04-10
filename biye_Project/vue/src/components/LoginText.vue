<template xmlns="http://www.w3.org/1999/html">
  <div>
    登陆界面<br>
    {{msg}}<br>
    {{user.name}}
  </div>
  <el-button @click="changeMsg('毕设')" type="primary">
    <el-icon style="vertical-align: middle">
      <Search />
    </el-icon>
    <span style="vertical-align: middle"> Update </span>
  </el-button>
  <el-button @click="changeUser('用户')" type="primary">
    <el-icon style="vertical-align: middle">
      <Edit />
    </el-icon>
    <span style="vertical-align: middle"> Add </span>
  </el-button>
</template>

<script setup>
  import {ref,reactive} from "vue";
  import {Search,Edit} from "@element-plus/icons-vue";
  const msg = ref("推免研究生评选管理系统")
  const changeMsg = (value) => {
    msg.value = value
  }

  const user = reactive({
        name: 'Tom'
      }
  )
  const changeUser = (name) => {
    user.name = name
  }
</script>