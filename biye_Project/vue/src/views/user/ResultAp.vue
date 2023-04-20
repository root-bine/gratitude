<template>
  <div class="ra">
    <el-card style="width: 600px;height: 650px; background-color: lightblue">
      <el-form ref="ruleFormRef"
               :model="state.baomingbiao" :rules="state.rules"
               label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="学生学号">
              <el-input v-model="state.baomingbiao.stuID"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="学生姓名">
              <el-input v-model="state.baomingbiao.stuName"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="专业名称">
              <el-input v-model="state.baomingbiao.profession"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="四/六级">
              <el-input v-model="state.baomingbiao.english"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="平均绩点">
              <el-input v-model="state.baomingbiao.average"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="报名结果" prop="file" style="background-color: red">
              <el-input v-model="state.baomingbiao.file"
                        disabled style="width: 400px; height: 35px;"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="报名结果" prop="audit" style="background-color: red">
              <el-input v-model="state.baomingbiao.audit"
                        disabled style="width: 400px; height: 35px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item style="margin-left: 170px;margin-top: 40px">
          <el-button type="primary" size="large" style="margin-right: 5px"
                     @click="$router.push({name: 'SearchApplication'})">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "../../utils/request.js";

const state = reactive({
  baomingbiao: {},
  rules: {
    file: [
      {required: true, trigger: 'blur'},
    ],
    audit: [
      {required: true, trigger: 'blur'},
    ],
  }
})
import {useRouter} from 'vue-router'
const router = useRouter()
const id = router.currentRoute.value.params.id
request.get('/add/search/'+id).then(res => {
  state.baomingbiao.stuID = res.data.stuID
  state.baomingbiao.stuName = res.data.stuName
  state.baomingbiao.profession = res.data.profession
  state.baomingbiao.file = res.data.file
  state.baomingbiao.audit = res.data.audit
  state.baomingbiao.english = res.data.english
  state.baomingbiao.average = res.data.average
})
</script>

<style scoped>
.ra {
  display: flex;
  background-image: url("../static/bg-row.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>