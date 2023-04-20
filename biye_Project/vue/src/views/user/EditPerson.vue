<template>
  <div class="edit">
    <el-card style="width: 600px;height: 680px;background-color: burlywood">
      <el-form :model="user" :rules="rules" ref="ruleFormRef" label-width="100px">
        <el-form-item label="学校" prop="college">
          <el-input v-model="user.college" autocomplete="off" style="width: 300px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="college">
          <el-input v-model="user.department" autocomplete="off" style="width: 300px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="user.profession" autocomplete="off" style="width: 300px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentid">
          <el-input v-model="user.studentid" autocomplete="off" style="width: 250px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="username">
          <el-input v-model="user.username" autocomplete="off" style="width: 250px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="user.sex">
            <el-radio label="男" size="large">男</el-radio>
            <el-radio label="女" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="user.age" autocomplete="off" style="width: 100px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item label="政治面貌" prop="region">
          <el-radio-group v-model="user.region">
            <el-radio label="普通群众" size="large"></el-radio>
            <el-radio label="共青团员" size="large"></el-radio>
            <el-radio label="预备党员" size="large"></el-radio>
            <el-radio label="正式党员" size="large"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" autocomplete="off" style="width: 250px;height: 35px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="Confirm">确认</el-button>
          <el-button @click="$router.push({name: 'Person'})">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {getCurrentInstance, reactive} from "vue";
import {ElMessage} from "element-plus";
import request from "../../utils/request.js";
const {proxy} = getCurrentInstance()
import {useRouter} from 'vue-router'
const router = useRouter()
const id = router.currentRoute.value.params.id
request.get('/users/one/'+id).then(res => {
  user.college = res.data.college,
      user.profession = res.data.profession,
      user.studentid = res.data.studentid,
      user.username = res.data.username,
      user.age = res.data.age,
      user.sex = res.data.sex,
      user.region =res.data.region,
      user.email = res.data.email,
      user.department = res.data.department
})
const user = reactive({})
const rules = reactive({
  college: [
    {required: true, message: '请输入学校名称', trigger: 'blur'},
  ],
  profession: [
    {required: true, message: '请输入专业名称', trigger: 'blur'},
  ],
  sex: [
    {required: true, message: '请选择性别', trigger: 'blur'},
  ],
  studentid: [
    {required: true, message: '请输入学号', trigger: 'blur'},
  ],
  username: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  age: [
    {required: true, message: '请输入年龄', trigger: 'blur'},
  ],
  region: [
    {required: true, message: '必须选择', trigger: 'blur'},
  ],
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
  ],
  department: [
    {required: true, message: '请输入学院', trigger: 'blur'},
  ]
})
const Confirm= ()=> {
  // 表单验证
  proxy.$refs.ruleFormRef.validate((valid) => {
    if (valid) { // 请求后台接口
      request.put("/users/message", user).then(res => {
        if(res.code === '200') {
          ElMessage({type: 'success', message: '修改成功^_^'})
          router.push({name: 'Person'})
        }
      })
    } else {
      ElMessage({type: 'error', message: '操作失败！！！'})
    }
  })
}
</script>

<style scoped>
.edit {
  display: flex;
  background-image: url("../static/bg-apple.jpg");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>