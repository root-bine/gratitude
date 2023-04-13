<template>
  <div class="waiver">
    <el-form ref="ruleFormRef"
             :model="baomingbiao" :rules="rules"
             label-width="100px">
      <el-row style="">
        <el-col :span="12">
          <el-form-item label="学号" prop="stuID" class="imset">
            <el-input v-model="shenqingbiao.stuID" placeholder="学生学号"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="stuName" class="imset">
            <el-input v-model="shenqingbiao.stuName" placeholder="学生姓名"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="专业" prop="profession" class="imset">
            <el-input v-model="shenqingbiao.profession" placeholder="专业名称"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学校名称" prop="school" class="imset">
            <el-input v-model="shenqingbiao.school" placeholder="学校名称"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="综测排名" prop="test" class="imset">
            <el-input v-model="shenqingbiao.test" placeholder="三年综测总排名"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="平均绩点" prop="average" class="imset">
            <el-input v-model="shenqingbiao.average" placeholder="三年平均绩点"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="相关附件" prop="file" class="imset">
            <el-upload
                v-model:file-list="shenqingbiao.file"
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
                  应届毕业证明、四六级证书、毕业体检报告
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
                      placeholder="挂科、重修" v-model="shenqingbiao.course"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="处分/奖励" prop="myself" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      placeholder="个人获奖或处分" v-model="shenqingbiao.myself"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button style="margin-left: 500px" size="large" type="primary"
                   @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script lang="ts" setup>
import {getCurrentInstance, reactive} from "vue";
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

import type { UploadProps, UploadUserFile } from 'element-plus'

//import router from "../router/index.js";
const {proxy} = getCurrentInstance()

const shenqingbiao = reactive({
  stuID: '',
  stuName: '',
  profession: '',
  test: '',
  average: '',
  course: '',
  file: [
    {
      name: 'element-plus-logo.svg',
      url: 'https://element-plus.org/images/element-plus-logo.svg',
    },
    {
      name: 'element-plus-logo2.svg',
      url: 'https://element-plus.org/images/element-plus-logo.svg',
    },
    {
      name: 'element-plus-logo3.svg',
      url: 'https://element-plus.org/images/element-plus-logo.svg',
    },
  ],
  myself: '',
  school: ''
})
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
    {required: true, message: '请输入综测排名', trigger: 'blur'},
  ],
  average: [
    {required: true, message: '请输入平均绩点', trigger: 'blur'},
  ],
  course: [
    {required: true, message: '请介绍专业修读情况', trigger: 'blur'},
  ],
  file: [
    {required: true, message: '请上传相关附件', trigger: 'blur'},
  ],
  myself: [
    {required: true, message: '请做自我介绍', trigger: 'blur'},
  ],
  school: [
    {required: true, message: '请输入学校名称', trigger: 'blur'},
  ],
})

const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  console.log(file, uploadFiles)
}

const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
  console.log(uploadFile)
}

const handleExceed: UploadProps['onExceed'] = (files, uploadFiles) => {
  ElMessage.warning(
      `The limit is 3, you selected ${files.length} files this time, add up to ${
          files.length + uploadFiles.length
      } totally`
  )
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
  color: brown;
  font-size: 10px;
}
</style>
