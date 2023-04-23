<template>
  <div class="newpassword">
    <div style="width: 400px; margin: 150px auto;">
      <el-form :model="user" :rules="rules" ref="ruleFormRef">
        <el-form-item label="UserNm" prop="studentid">
          <el-input v-model="user.studentid" style="margin-left: 20px" autocomplete="off" placeholder="请输入用户名" clearable/>
        </el-form-item>
        <el-form-item label="NewPass" prop="password">
          <el-input v-model="user.password" style="margin-left: 15px" autocomplete="off" placeholder="请输入新密码" clearable show-password/>
        </el-form-item>
        <el-form-item label="Confirm" prop="confirm">
          <el-input v-model="user.confirm" style="margin-left: 20px" autocomplete="off" placeholder="请确认密码" clearable show-password/>
        </el-form-item>
        <el-form-item style="margin-left: 100px">
          <el-button type="success" size="large" style="margin-right: 5px" @click="updatePass">确认</el-button>
          <el-button type="primary" size="large" style="margin-right: 5px" @click="$router.push({name: 'SuperPage'})">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {getCurrentInstance, reactive} from 'vue'
import router from "../../router/index.js"
import request from "../../utils/request.js";
import {ElMessage, ElNotification} from "element-plus";
const {proxy} = getCurrentInstance()
const user = reactive({})
// 表单验证
const rules = reactive({
  confirm: [
    {required: true, message: '请确认密码', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
  ],
  studentid: [
    {required: true, message: '请输入学号', trigger: 'blur'},
  ]
})
const updatePass = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      if(user.password !== user.confirm) {
        ElMessage.error("密码不一致")
        return false
      }
      request.put('/super/change',user).then(res => {
        if(res.code === '200'){ // 请求成功
          ElMessage({
            type: 'success',
            message: '修改成功'})
          /*登录成功, 进行页面跳转*/
          router.push("/")
        }else{ // 请求失败
          ElNotification({
            type: 'error',
            message: res.msg
          })
        }
      })
    }else{
      ElNotification({
        type: 'error',
        message: res.msg
      })
    }
  }
  )
}
</script>

<style scoped>
.newpassword {
  display: flex;
  background-image: url("../static/the dock.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>