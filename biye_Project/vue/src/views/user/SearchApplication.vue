<template>
<!--报名结果查询-->
  <div class="sa">
    <div style="width: 400px; margin: 150px auto;">
      <h1 style="text-align: center; font-size: 30px;color: aquamarine">推免报名查询界面</h1>

      <el-form :model="baomingbiao">
        <el-row>
          <el-col :span="24" style="font-size: 20px;color: red">
            注意事项:
          </el-col>
          <el-col :span="24" style="font-size: 18px;">
            1、确保已经完成个人信息的完善;<br>
            2、该查询只能查询本人审核状态, 无法查询他人;<br>
            3、需完成推免资格申请, 才能查询到有效信息;<br>
          </el-col>
        </el-row>
        <el-form-item style="margin-left: 170px;margin-top: 40px">
          <el-button type="primary" size="large" style="margin-right: 5px"
                     @click="$router.push({name: 'ResultAp'})">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
const baomingbiao = reactive({})
</script>

<style scoped>
.sa {
  display: flex;
  background-image: url("../static/bg-row.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>