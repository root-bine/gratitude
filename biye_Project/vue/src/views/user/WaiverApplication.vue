<template>
  <div class="waiver">
    <el-form ref="ruleFormRef"
             :model="application" :rules="rules"
             label-width="100px">
      <el-row style="">
        <el-col :span="12">
          <el-form-item label="学号" prop="stuID" class="imset">
            <el-input v-model="application.stuID" placeholder="学生学号"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="stuName" class="imset">
            <el-input v-model="application.stuName" placeholder="学生姓名"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="专业" prop="profession" class="imset">
            <el-input v-model="application.profession" placeholder="专业名称"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学校名称" prop="school" class="imset">
            <el-input v-model="application.school" placeholder="学校名称"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="综测成绩" prop="test" class="imset">
            <el-input v-model="application.test" placeholder="三年平均综测成绩"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="平均绩点" prop="average" class="imset">
            <el-input v-model="application.average" placeholder="三年平均绩点"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="相关附件" prop="file" class="imset">
            <el-upload
                v-model:file-list="application.file"
                class="upload-demo"
                action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                multiple
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                :limit="3"
                :on-exceed="handleExceed"
            >
              <el-button type="primary">Click to upload</el-button>
              <template #tip>
                <div class="el-upload__tip">
                  压缩包(应届毕业证明、四六级证书、毕业体检报告), , 后缀: .zip
                </div>
              </template>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="专业课程" prop="course" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      placeholder="挂科、重修" v-model="application.course"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="处分/奖励" prop="myself" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      placeholder="个人获奖或处分" v-model="application.myself"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button style="margin-left: 500px;margin-top: 20px" size="large" type="primary"
                   @click="Submit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {getCurrentInstance, reactive} from "vue";
import {ElMessage, ElNotification} from 'element-plus'
//import type { UploadProps, UploadUserFile } from 'element-plus'
import request from "../../utils/request.js";
import router from "../../router/index.js";

const {proxy} = getCurrentInstance()
const application = reactive({})
const rules = reactive({
  stuID: [
    {required: true, message: '请输入学号', trigger: 'blur'},
  ],
  stuName: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  profession: [
    {required: true, message: '请输入专业', trigger: 'blur'},
  ],
  test: [
    {required: true, message: '请输入平局综测成绩', trigger: 'blur'},
  ],
  average: [
    {required: true, message: '请输入平均绩点', trigger: 'blur'},
  ],
  course: [
    {required: true, message: '请介绍专业修读情况', trigger: 'blur'},
  ],
  myself: [
    {required: true, message: '请做自我介绍', trigger: 'blur'},
  ],
  school: [
    {required: true, message: '请输入学校名称', trigger: 'blur'},
  ],
})
const Submit = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      request.post('/apply', application).then(res => {
        if(res.code === '200'){ // 请求成功
          ElMessage({
            type: 'success',
            message: '上传成功'})
          /*登录成功, 进行页面跳转*/
          router.push({name: 'WaiverLgo'})
        }else{ // 请求失败
          ElNotification({
            type: 'error',
            message: res.msg
          })
        }
      })
    }
  })
}

</script>

<style>
.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
}
.waiver {
  display: flex;
  background-image: url("../static/redLight.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
.imset .el-form-item__label{
  color: cyan;
}
.el-upload__tip {
  color: lightblue;
  font-size: 10px;
}
</style>
