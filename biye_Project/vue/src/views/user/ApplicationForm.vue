<template>
  <div class="applicate">
    <el-form ref="ruleFormRef"
             :model="baomingbiao" :rules="rules"
             label-width="100px" style="margin-left: 35px;margin-top: 10px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="学号" prop="stuID" class="imset">
            <el-input v-model="baomingbiao.stuID" placeholder="学生学号"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="stuName" class="imset">
            <el-input v-model="baomingbiao.stuName" placeholder="学生姓名"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="性别" prop="sex" class="imset">
            <el-radio-group v-model="baomingbiao.sex" class="ml-4">
              <el-radio label="男" size="default" border/>
              <el-radio label="女" size="default" border/>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="专业" prop="profession" class="imset">
            <el-input v-model="baomingbiao.profession" placeholder="专业名称"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="四/六级" prop="english" class="imset">
            <el-radio-group v-model="baomingbiao.english" class="ml-4">
              <el-radio label="ETC-4" size="default" border/>
              <el-radio label="ETC-6" size="default" border/>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="平均绩点" prop="average" class="imset">
            <el-input v-model="baomingbiao.average" placeholder="三年平均绩点"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="政治面貌" prop="stuStatus" class="imset">
            <el-radio-group v-model="baomingbiao.stuStatus" class="ml-4">
              <el-radio label="普通群众" size="large" border></el-radio>
              <el-radio label="共青团员" size="large" border></el-radio>
              <el-radio label="预备党员" size="large" border></el-radio>
              <el-radio label="正式党员" size="large" border></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="电话号码" prop="phone" class="imset">
            <el-input v-model="baomingbiao.phone" placeholder="11位有效号码"
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="24">
        <el-form-item label="相关附件" class="imset">
          <el-upload
              class="upload-demo"
              :action="uploadUrl"
              :file-list="fileList"
              :on-success="handleSuccess">
            <el-button size="large" type="danger">点击上传</el-button>
            <template #tip>
              <div class="el-upload__tip">
                压缩包(推免报名表、综合信息表、相关证明材料), 后缀: .zip
              </div>
            </template>
          </el-upload>
        </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="毕设内容" prop="gratitude" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      placeholder="毕设题目，及概述主要内容" v-model="baomingbiao.gratitude"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="自我介绍" prop="myself" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      placeholder="对个人进行描述" v-model="baomingbiao.myself"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" style="margin-left: 400px;margin-top: 20px"
                   size="large" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script setup>
import {getCurrentInstance, reactive, ref} from "vue";
import {ElMessage, ElMessageBox, ElNotification} from 'element-plus'
import request from "../../utils/request.js";
//import type { UploadProps, UploadUserFile } from 'element-plus'
import router from "../../router/index.js";
const {proxy} = getCurrentInstance()
const baomingbiao = reactive({})
const rules = reactive({
  stuID: [
    {required: true, message: '请输入学号', trigger: 'blur'},
  ],
  stuName: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  sex: [
    {required: true, message: '请选择性别', trigger: 'blur'},
  ],
  profession: [
    {required: true, message: '请输入专业', trigger: 'blur'},
  ],
  english: [
    {required: true, message: '请输入英语成绩', trigger: 'blur'},
  ],
  average: [
    {required: true, message: '请输入平均绩点', trigger: 'blur'},
  ],
  gratitude: [
    {required: true, message: '请输入毕设内容', trigger: 'blur'},
  ],
  stuStatus: [
    {required: true, message: '请选择政治面貌', trigger: 'blur'},
  ],
  phone: [
    {required: true, message: '请填写电话号码', trigger: 'blur'},
  ],
  myself: [
    {required: true, message: '请做自我介绍', trigger: 'blur'},
  ],
})
const onSubmit = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      request.post('/add', baomingbiao).then(res => {
        if(res.code === '200'){ // 请求成功
          ElMessage({
            type: 'success',
            message: '上传成功'})
          /*登录成功, 进行页面跳转*/
          router.push({name: 'ConsolidatedTable'})
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
const fileList = ref([]);
const uploadUrl = 'http://localhost:9090/file/upload';
const handleSuccess = (response, file) => {
  ElMessage.success('上传成功')
  fileList.value.push({
    name: file.name,
    url: URL.createObjectURL(file.raw),
  })
}
</script>

<style>
.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
}
.applicate {
  display: flex;
  background-image: url("../static/bg-girl.jpg");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
.imset .el-form-item__label{
  color: darkorange;
}
.el-upload__tip {
  color: lightblue;
  font-size: 10px;
}
.ml-4 .el-radio__input.is-checked + .el-radio__label{
  color: black;
}
</style>
