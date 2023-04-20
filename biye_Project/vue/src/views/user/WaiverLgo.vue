<template>
  <div class="waiver">
    <el-form ref="ruleFormRef"
             :model="state.application" :rules="rules"
             label-width="100px">
      <el-row style="">
        <el-col :span="12">
          <el-form-item label="学号" class="imset">
            <el-input :readonly="true" v-model="state.application.stuID"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" class="imset">
            <el-input v-model="state.application.stuName"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="专业" class="imset">
            <el-input v-model="state.application.profession"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学校名称" class="imset">
            <el-input v-model="state.application.school"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="综测成绩" class="imset">
            <el-input v-model="state.application.test"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="平均绩点" class="imset">
            <el-input v-model="state.application.average"
                      disabled style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

<!--        <el-col :span="24">
          <el-form-item label="相关附件" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.application.file"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>-->
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="专业课程" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.application.course"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="处分/奖励" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.application.myself"
                      type="textarea"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button style="margin-left: 380px;margin-top: 20px" size="large" type="primary"
                   @click="handleEdit">修改</el-button>
        <el-popconfirm title="确定要删除该记录 ?" confirm-button-text="确定"
                       cancel-button-text="取消" @confirm="handleDelete">
          <template #reference>
            <el-button style="margin-left: 150px;margin-top: 20px"
                size="large" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </el-form-item>
    </el-form>
  </div>

  <el-dialog v-model="dialogFormVisible" title="编辑信息" width="50%">
    <el-form :model="state.application" :rules="rules" ref="ruleFormRef" label-width="100px">
      <el-form-item label="学号" prop="stuID">
        <el-input v-model="state.application.stuID" autocomplete="off" placeholder="请输入学号" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="姓名" prop="stuName">
        <el-input v-model="state.application.stuName" autocomplete="off" placeholder="请输入姓名" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="学校" prop="school">
        <el-input v-model="state.application.school" autocomplete="off" placeholder="请输入学校名称" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="专业" prop="profession">
        <el-input v-model="state.application.profession" autocomplete="off" placeholder="请输入专业名称" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="综测成绩" prop="test">
        <el-input v-model="state.application.test" autocomplete="off" placeholder="请填写平均综测成绩" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="平均绩点" prop="average">
        <el-input v-model="state.application.average" autocomplete="off" placeholder="请输入平均绩点" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="专业课情况" prop="course">
        <el-input  type="textarea" v-model="state.application.course" autocomplete="off" placeholder="重修/挂科情况" style="width: 60%"/>
      </el-form-item>
      <el-form-item label="奖励/处分" prop="myself">
        <el-input  type="textarea" v-model="state.application.myself" autocomplete="off" placeholder="奖励/处分" style="width: 60%"/>
      </el-form-item>
      <el-form-item label="相关文件">
        <el-upload
            action="http://localhost:8080/api/upload"
            :auto-upload="false"
            :on-change="handleUploadChange"
            :file-list="fileList">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="uploadFile">上传文件</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-popconfirm title="请确认信息是否有误？" confirm-button-text="确定"
                       cancel-button-text="取消" @confirm="Confirm">
          <template #reference>
            <el-button size="20" type="danger">确定</el-button>
          </template>
        </el-popconfirm>
        <el-button @click="dialogFormVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {getCurrentInstance, reactive, ref} from "vue";
import request from "../../utils/request.js";
import {ElMessage} from "element-plus";
import {useRouter} from 'vue-router'
/*// 表单校验引入
const {proxy} = getCurrentInstance()
// 弹窗配置
const dialogFormVisible = ref(false)
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

const state = reactive({
  application : {}
})
const router = useRouter()
const id = router.currentRoute.value.params.id
request.get('/apply/'+id).then(res => {
  state.application.stuID = res.data.stuID
  state.application.stuName = res.data.stuName
  state.application.profession = res.data.profession
  state.application.test = res.data.test
  state.application.average = res.data.average
  state.application.course = res.data.course
  state.application.file = res.data.file
  state.application.school = res.data.school
  state.application.myself = res.data.myself
})

// 编辑
  const handleEdit = (row) => {
    dialogFormVisible.value = true
    state.application = JSON.parse(JSON.stringify(row))
  }
// 删除
  const handleDelete = () => {
    request.delete("/apply/" + id).then(res => {
      if (res.code === '200' && res.data === true) {
        ElMessage.success("删除成功！")
        router.push({name: 'FrontPage'})
      } else {
        ElMessage.error(res.msg)
      }
    })
  }
// 确定选项
  const Confirm = () => {
    // 表单验证
    proxy.$refs.ruleFormRef.validate((valid) => {
      if (valid === true) { // 请求后台接口
          request.put("/apply", state.application).then(res => {
            if (res.code === '200') {
              ElMessage({type: 'success', message: '修改成功！！！'})
              /!*关闭弹窗*!/
              dialogFormVisible.value = false
            } else {
              ElMessage({type: 'error', message: res.msg})
            }
          })
      } else {
        ElMessage({type: 'error', message: '操作失败！！！'})
      }
    })
  }*/
export default {
  name: 'FileUpload',
  setup() {
    const fileList = ref([]);
    function handleUploadChange(file, fileList) {
      console.log('uploaded:', file, fileList);
    }
    function uploadFile() {
      // 进行文件上传，可使用axios等工具
      console.log('uploading files:', fileList.value);
    }
    // 表单校验引入
    const {proxy} = getCurrentInstance()
// 弹窗配置
    const dialogFormVisible = ref(false)
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

    const state = reactive({
      application : {}
    })
    const router = useRouter()
    const id = router.currentRoute.value.params.id
    request.get('/apply/'+id).then(res => {
      state.application.stuID = res.data.stuID
      state.application.stuName = res.data.stuName
      state.application.profession = res.data.profession
      state.application.test = res.data.test
      state.application.average = res.data.average
      state.application.course = res.data.course
      state.application.file = res.data.file
      state.application.school = res.data.school
      state.application.myself = res.data.myself
    })

// 编辑
    const handleEdit = (row) => {
      dialogFormVisible.value = true
      state.application = JSON.parse(JSON.stringify(row))
    }
// 删除
    const handleDelete = () => {
      request.delete("/apply/" + id).then(res => {
        if (res.code === '200' && res.data === true) {
          ElMessage.success("删除成功！")
          router.push({name: 'FrontPage'})
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
// 确定选项
    const Confirm = () => {
      // 表单验证
      proxy.$refs.ruleFormRef.validate((valid) => {
        if (valid === true) { // 请求后台接口
          request.put("/apply", state.application).then(res => {
            if (res.code === '200') {
              ElMessage({type: 'success', message: '修改成功！！！'})
              /*关闭弹窗*/
              dialogFormVisible.value = false
            } else {
              ElMessage({type: 'error', message: res.msg})
            }
          })
        } else {
          ElMessage({type: 'error', message: '操作失败！！！'})
        }
      })
    }
    return {
      fileList,
      handleUploadChange,
      uploadFile,
      handleEdit,
      handleDelete,
      Confirm,
      state,
      rules,
      dialogFormVisible
    }
  }
}
</script>

<style scoped>
.waiver {
  display: flex;
  background-image: url("../static/redLight.png");
  width: 100%;
  height: 680px;
  justify-content: center;
  align-items: center;
}
</style>

<style>
.imset .el-form-item__label{
  color: cyan;
}
</style>