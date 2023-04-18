<template>
  <div class="rw">
    <el-card style="width: 600px;height: 650px;background-color: lightblue">
      <el-form ref="ruleFormRef"
               :model="state.application" :rules="state.rules"
               label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="学号">
              <el-input v-model="state.application.stuID"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="姓名">
              <el-input v-model="state.application.stuName"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="专业">
              <el-input v-model="state.application.profession"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="综测成绩">
              <el-input v-model="state.application.test"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="平均绩点">
              <el-input v-model="state.application.average"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="申请学校">
              <el-input v-model="state.application.school"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="申请结果" prop="audit" style="background-color: red">
              <el-input v-model="state.application.audit"
                        disabled style="width: 400px;height: 35px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item style="margin-left: 170px;margin-top: 40px">
          <el-button type="primary" size="large" style="margin-right: 5px"
                     @click="$router.push({name: 'SearchWaiver'})">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
  import {reactive} from "vue";
  import request from "../../utils/request.js";

  const state = reactive({
    application: {},
    rules: {
      audit: [
        {required: true, trigger: 'blur'},
      ],
    }
  })
  import {useRouter} from 'vue-router'
  const router = useRouter()
  const id = router.currentRoute.value.params.id
  request.get('/apply/searching/'+id).then(res => {
    state.application.stuID = res.data.stuID
    state.application.stuName = res.data.stuName
    state.application.school = res.data.school
    state.application.audit = res.data.audit
    state.application.profession = res.data.profession
    state.application.test = res.data.test
    state.application.average = res.data.average
    console.log(res.data)
  })
</script>

<style scoped>
.rw {
  display: flex;
  background-image: url("../static/bg-row.jpg");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>