<template>
  <div class="person">
    <el-form :model="user"
             label-width="100px">
      <el-row>
        <el-col :span="24">
          <el-upload
              class="avatar-uploader"
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学校" class="setLavle">
            <el-input v-model="state.form.college" disabled style="width: 300px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="专业" class="setLavle">
            <el-input v-model="state.form.profession" disabled style="width: 300px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" class="setLavle">
            <el-input v-model="state.form.username" disabled style="width: 250px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学号" class="setLavle">
            <el-input v-model="state.form.studentid" disabled style="width: 250px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" class="setLavle">
            <el-input v-model="state.form.sex" disabled style="width: 100px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="年龄" class="setLavle">
            <el-input v-model="state.form.age" disabled style="width: 100px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="政治面貌" class="setLavle">
            <el-input v-model="state.form.region" disabled style="width: 250px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" class="setLavle">
            <el-input v-model="state.form.email" disabled style="width: 250px;height: 35px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import {getCurrentInstance, reactive} from "vue";
  import { ref } from 'vue'
  import { ElMessage } from 'element-plus'
  import { Plus } from '@element-plus/icons-vue'
  import request from '../../utils/request.js'
  import type { UploadProps } from 'element-plus'
  const imageUrl = ref('')
  const {proxy} = getCurrentInstance()
  const handleAvatarSuccess: UploadProps['onSuccess'] = (
      response,
      uploadFile
  ) => {
    imageUrl.value = URL.createObjectURL(uploadFile.raw!)
  }

  const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
    if (rawFile.type !== 'image/jpeg') {
      ElMessage.error('Avatar picture must be JPG format!')
      return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
      ElMessage.error('Avatar picture size can not exceed 2MB!')
      return false
    }
    return true
  }
  import {reactive, ref} from "vue";
  import {useRouter} from 'vue-router'
  const router = useRouter()
  const state = reactive({
    form: {},
  })
  const id = router.currentRoute.value.params.id
  request.get('/users/one/'+id).then(res => {
    state.form = res.data
  })
</script>

<style scoped>
.avatar-uploader {
  text-align: center;
  padding-bottom: 20px;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.avatar-uploader-icon {
  font-size: 28px;
  color: burlywood;
  width: 178px;
  height: 178px;
  text-align: center;
}
.ml-4 {
    margin-left: 450px;
    margin-top: 25px;
}
.setLavle {
  margin-left: 35px;
  margin-top: 30px;
}
.person {
  display: flex;
  background-image: url("../static/cat.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>

<style>
.setLavle .el-form-item__label {
  color: cyan;
}
</style>