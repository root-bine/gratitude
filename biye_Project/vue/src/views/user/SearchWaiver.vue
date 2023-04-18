<template>
<!--申请结果查询-->
  <div class="sw">
    <div style="width: 400px; margin: 150px auto;">
      <h1 style="text-align: center; font-size: 30px; margin-bottom: 30px;color: darksalmon">推免申请查询页面</h1>
      <el-form :model="state.application" :rules="rules" ref="ruleFormRef">
        <el-row>
          <el-col :span="24" style="font-size: 20px;color: red">
            注意事项:
          </el-col>
          <el-col :span="24" style="font-size: 18px;">
            1、确保已经完成个人信息的完善;<br>
            2、该查询只能查询本人审核状态, 无法查询他人;<br>
            3、需完成个人推免报名, 才能查询到有效信息;<br>
          </el-col>
        </el-row>
        <el-form-item style="margin-left: 170px;margin-top: 40px">
          <el-button type="primary" size="large" style="margin-right: 5px"
                     @click="$router.push({name: 'ResultWa'})">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import request from "../../utils/request.js";

const state = reactive({
  application: {}
})
const rules = reactive({
  stuID: [
    {required: true, message: '请输入学号', trigger: 'blur'},
  ],
  stuName: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ]
})
</script>

<style scoped>
  .sw {
    display: flex;
    background-image: url("../static/bg-row.jpg");
    width: 100%;
    height: 680px;
    justify-content: center;
    align-items: center;
  }
  .imset {
    text-align: center;
  }
</style>