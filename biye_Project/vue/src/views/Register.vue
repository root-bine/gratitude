<template>
  <div class="logincontent">
    <div style="width: 400px; margin: 150px auto;">
      <h1 style="text-align: center; margin-bottom: 30px">推免研究生注册界面</h1>
      <!--:rules="rules" ref="ruleFormRef", 表单验证标识-->
      <el-form :model="user" size="large" :rules="rules" ref="ruleFormRef">
        <el-form-item prop="username"> <!--label="用  户"-->
          <el-input v-model="user.username" :prefix-icon="User" clearable/>
        </el-form-item>
        <!--prop标签内容, 与rules对应-->
        <el-form-item prop="password"> <!--label="密  码"-->
          <el-input v-model="user.password" :prefix-icon="Lock" show-password clearable/>
        </el-form-item>
        <el-form-item prop="confirm"> <!--再次确认密码-->
          <el-input v-model="user.confirm" :prefix-icon="Pointer" show-password clearable/>
        </el-form-item>
        <el-form-itemv style="margin-left: 25px">
          <el-button type="primary" style="width: 40%;margin-right: 5px" @click="register">注册</el-button>
          <el-button type="danger" style="width: 40%;margin-right: 5px" @click="$router.push('/login')">返回</el-button>
        </el-form-itemv>
      </el-form>
    </div>
  </div>
</template>

<script setup>
  // 引入router, 便于页面跳转
  import router from "../router/index.js";

  import {getCurrentInstance, reactive} from "vue";
  import {User,Lock,Pointer} from "@element-plus/icons-vue";
  import {ElMessage,ElNotification} from "element-plus";
  import request from "../utils/request.js";
  const {proxy} = getCurrentInstance()

  const user = reactive({})
  // 表单验证
  const rules = reactive({
    username: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
    ],
    confirm: [
      {required: true, message: '请确认密码', trigger: 'blur'},
    ]
  })
  // 登录实现
  const register = () => {
    proxy.$refs.ruleFormRef.validate((valid) => {
          if(valid){
            if(user.password !== user.confirm) {
              ElMessage.error("密码不一致")
              return false
            }
            request.post('/users/register',user).then(res => {
              if(res.code === '200'){ // 请求成功
                ElMessage({
                  type: 'success',
                  message: '注册成功'})
                /*注册成功, 进行页面跳转*/
                router.push("/login")
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
              message: '用户名或密码错误'
            })
          }
        }
    )
  }
</script>

<style scoped>
.logincontent {
  display: flex;
  background-image: url("../assets/future.png");
  width: 100%;
  height: 100%;
  min-width: 100px;
  min-height: 760px;
  justify-content: center;
  align-items: center;
}
</style>