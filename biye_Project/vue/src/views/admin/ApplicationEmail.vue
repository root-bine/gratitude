<template>
  <div class="email">
    <el-card style="width: 600px;height: 650px; background-color: lightblue">
      <h1 style="text-align: center;font-size: 30px">
        推免申请邮件发送
      </h1>
      <el-form :model="state.form"
               :rules="state.rules" ref="ruleFormRef"
               label-width="100px">
        <el-row>
          <el-col :span="24" style="font-size: 20px;color: red;margin-left: 20px">
            注意事项:
          </el-col>
          <el-col :span="24" style="font-size: 18px;margin-left: 35px">
            1、需完成申请的审核, 并生成拟定名单;<br>
            2、查找报考所属学院的学生邮箱, 进行邮件通知;<br>
            3、确认邮箱和内容后, 即可进行后续操作;<br>
          </el-col>
          <el-col :span="24">
            <el-form-item label="邮箱" prop="to" style="width: 400px;height: 35px;margin-top: 30px">
              <el-input v-model="state.form.to" autocomplete="off"
                        placeholder="请输入邮箱地址"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="标题" prop="subject" style="width: 400px;height: 35px;margin-top: 30px">
              <el-input  type="textarea" v-model="state.form.subject" autocomplete="off"
                         placeholder="请设置标题"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="内容" prop="content" style="width: 400px;height: 35px;margin-top: 30px">
              <el-input  type="textarea" v-model="state.form.content" autocomplete="off"
                         placeholder="请填写邮件内容"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item style="margin-left: 170px;margin-top: 40px">
          <el-button type="primary" @click="load">确认</el-button>
          <el-button type="danger" @click="$router.push({name: 'UserList'})">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import request from "../../utils/request.js";
import {ElMessage} from "element-plus";
import router from "../../router/index.js";
import {reactive, ref} from "vue";
// 表单校验引入
const state = reactive({
  tableData: [],
  form: {},
  rules: {
    to: [
      {required: true, message: '请输入邮箱地址', trigger: 'blur'},
    ],
    subject: [
      {required: true, message: '请输入标题', trigger: 'blur'},
    ],
    content: [
      {required: true, message: '请填写邮件内容', trigger: 'blur'},
    ]
  }
})

const load= ()=>{
  request.post("/email", state.form).then(res => {
    if(res.code === '200') {
      ElMessage({type: 'success', message: '发送成功！！！'})
      router.push({name: 'UserList'})
    }else {
      ElMessage.error(res.msg)
    }
  })
}
</script>

<style scoped>
.email {
  display: flex;
  background-image: url("../static/bg-row.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>