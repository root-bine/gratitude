<template>
  <div class="logincontent">
    <div style="width: 400px; margin: 150px auto;" class="window">
      <h1 style="text-align: center; margin-bottom: 30px">推免研究生登录界面</h1>
      <!--:rules="rules" ref="ruleFormRef", 表单验证标识-->
      <el-form :model="OBJECT" :rules="rules" ref="ruleFormRef">
        <el-form-item prop="studentid">
          <el-input style="height: 50px;width: 500px" v-model="OBJECT.studentid" :prefix-icon="User" clearable/>
        </el-form-item>
        <!--prop标签内容, 与rules对应-->
        <el-form-item prop="password">
          <el-input style="height: 50px" v-model="OBJECT.password" :prefix-icon="Lock" show-password clearable/>
        </el-form-item>
        <el-form-item style="margin-left: 100px">
          <el-radio-group v-model="radio" class="ml-4">
            <el-radio label="1" size="large" >管理员</el-radio>
            <el-radio label="2" size="large">学生</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item style="margin-left: 100px">
          <el-button type="success" size="large" style="margin-right: 5px" @click="login">登录</el-button>
          <el-button type="primary" size="large" style="margin-right: 5px" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
  // 引入router, 便于页面跳转
  import router from "../router/index.js";
  import {getCurrentInstance, reactive, ref} from "vue";
  import {User,Lock} from "@element-plus/icons-vue";
  import {ElMessage,ElNotification} from "element-plus";
  import request from "../utils/request.js";
  const {proxy} = getCurrentInstance()

  const OBJECT = reactive({
        /*username: 'Tom',
        password: 123456*/
  })
  // 表单验证
  const rules = reactive({
    studentid: [
      {required: true, message: '请输入学号/root用户', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
    ]
  })
  const radio = ref(1)
  // 登录实现
  const login = () => {
    proxy.$refs.ruleFormRef.validate((valid) => {
          if(valid && radio.value == 2){
            /**
             * 向后台发送请求:http://localhost:9090
             * 后端数据格式: {"code" : "200","msg" : " ",，"data": null}
             * 此处请求为Post, 后端登录功能接口也必须是Post
             */
            request.post('/users/login',OBJECT).then(res => {
              if(res.code === '200'){ // 请求成功
                ElMessage({
                  type: 'success',
                  message: '登录成功'})
                /*登录成功, 进行页面跳转*/
                router.push({name: 'FrontPage', params:{id: res.data.id}})
              }else{ // 请求失败
                ElNotification({
                  type: 'error',
                  message: res.msg
                })
              }
            })
          } else if(valid && radio.value == 1) {
            request.post('/root/in', OBJECT).then(res => {
              if(res.code === '200'){ // 请求成功
                ElMessage({
                  type: 'success',
                  message: '登录成功'})
                /*登录成功, 进行页面跳转*/
                router.push({name: 'AdminPag'})
              }else{ // 请求失败
                ElNotification({
                  type: 'error',
                  message: res.msg
                })
              }
            })
          } else{
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
.logincontent {
  display: flex;
  background-image: url("../static/wyy8.png");
  width: 100%;
  height: 100%;
  min-width: 100px;
  min-height: 760px;
  justify-content: center;
  align-items: center;
}
</style>
<style>
/*去除图片白边*/
html,body{
  margin:0;
  padding:0;
}
</style>