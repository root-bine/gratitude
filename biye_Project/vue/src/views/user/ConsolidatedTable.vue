<template>
  <div class="applicate">
    <el-form ref="ruleFormRef"
             :model="state.baomingbiao" :rules="state.rules"
             label-width="100px" style="margin-left: 35px;margin-top: 10px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="学号" prop="stuID" class="imset">
            <el-input v-model="state.baomingbiao.stuID" disabled
                      clearable style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="stuName" class="imset">
            <el-input v-model="state.baomingbiao.stuName" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="性别" prop="sex" class="imset">
            <el-input v-model="state.baomingbiao.sex" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="专业" prop="profession" class="imset">
            <el-input v-model="state.baomingbiao.profession" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="四/六级" prop="english" class="imset">
            <el-input v-model="state.baomingbiao.english" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="平均绩点" prop="average" class="imset">
            <el-input v-model="state.baomingbiao.average" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="政治面貌" prop="stuStatus" class="imset">
            <el-input v-model="state.baomingbiao.stuStatus" disabled
                      style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="电话号码" prop="phone" class="imset">
            <el-input v-model="state.baomingbiao.phone" disabled
                       style="width: 400px;height: 35px"/>
          </el-form-item>
        </el-col>

<!--        <el-col :span="24">
          <el-form-item label="相关附件" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.baomingbiao.file"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>-->
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="毕设内容" prop="gratitude" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.baomingbiao.gratitude"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="自我介绍" prop="myself" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="3"
                      disabled v-model="state.baomingbiao.myself"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" style="margin-left: 400px;margin-top: 20px"
                   size="large" @click="handleEdit">修改</el-button>
        <el-popconfirm title="确定要删除该记录 ?" confirm-button-text="确定"
                       cancel-button-text="取消" @confirm="handleDelete">
          <template #reference>
            <el-button style="margin-left: 150px;margin-top: 20px"
                       size="large" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </el-form-item>
    </el-form>

    <el-dialog v-model="dialogFormVisible1" title="编辑信息" width="50%">
      <el-form :model="state.baomingbiao" :rules="rules" ref="ruleFormRef" label-width="100px">
        <el-form-item label="学号" prop="stuID">
          <el-input v-model="state.baomingbiao.stuID" autocomplete="off" placeholder="请输入学号" style="width: 20%"/>
        </el-form-item>
        <el-form-item label="姓名" prop="stuName">
          <el-input v-model="state.baomingbiao.stuName" autocomplete="off" placeholder="请输入姓名" style="width: 20%"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="state.baomingbiao.sex">
            <el-radio label="男" size="large">男</el-radio>
            <el-radio label="女" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="专业" prop="profession">
          <el-input v-model="state.baomingbiao.profession" autocomplete="off" placeholder="请输入专业名称" style="width: 35%"/>
        </el-form-item>
        <el-form-item label="四/六级" prop="english">
          <el-radio-group v-model="state.baomingbiao.english" class="ml-4">
            <el-radio label="ETC-4" size="default" border/>
            <el-radio label="ETC-6" size="default" border/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="平均绩点" prop="average">
          <el-input v-model="state.baomingbiao.average" autocomplete="off" placeholder="请输入平均绩点" style="width: 35%"/>
        </el-form-item>
        <el-form-item label="毕设内容" prop="gratitude">
          <el-input  type="textarea" v-model="state.baomingbiao.gratitude" autocomplete="off" placeholder="毕设内容" style="width: 60%"/>
        </el-form-item>
        <el-form-item label="政治面貌" prop="stuStatus">
          <el-radio-group v-model="state.baomingbiao.stuStatus">
            <el-radio label="普通群众" size="large"></el-radio>
            <el-radio label="共青团员" size="large"></el-radio>
            <el-radio label="预备党员" size="large"></el-radio>
            <el-radio label="正式党员" size="large"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input  type="textarea" v-model="state.baomingbiao.phone" autocomplete="off" placeholder="请输入电话号码" style="width: 60%"/>
        </el-form-item>
        <el-form-item label="自我介绍" prop="myself">
          <el-input  type="textarea" v-model="state.baomingbiao.myself" autocomplete="off" placeholder="请进行自我描述" style="width: 60%"/>
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
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {getCurrentInstance, reactive} from "vue";
import { ref } from 'vue'
import {ElMessage} from 'element-plus'
import request from "../../utils/request.js";
import {useRouter} from 'vue-router'
export default {
  name: 'FileUpload',
  setup() {
    const fileList = ref([]);
    const dialogFormVisible1 = ref(false)
    const {proxy} = getCurrentInstance()
    const state = reactive({
      baomingbiao : {},
      rules: {
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
        /*file: [
          {required: true, message: '请上传相关附件', trigger: 'blur'},
        ],*/
        myself: [
          {required: true, message: '请做自我介绍', trigger: 'blur'},
        ],
      }
    })

    const router = useRouter()
    const id = router.currentRoute.value.params.id
    request.get('/add/'+id).then(res => {
      state.baomingbiao.stuID = res.data.stuID
      state.baomingbiao.stuName = res.data.stuName
      state.baomingbiao.sex = res.data.sex
      state.baomingbiao.profession = res.data.profession
      state.baomingbiao.english = res.data.english
      state.baomingbiao.gratitude = res.data.gratitude
      state.baomingbiao.average = res.data.average
      state.baomingbiao.stuStatus = res.data.stuStatus
      state.baomingbiao.file = res.data.file
      state.baomingbiao.phone = res.data.phone
      state.baomingbiao.myself = res.data.myself
    })
    function handleUploadChange(file, fileList) {
      console.log('uploaded:', file, fileList);
    }
    function uploadFile() {
      // 进行文件上传，可使用axios等工具
      console.log('uploading files:', fileList.value);
    }
    function handleEdit(row) {
      dialogFormVisible1.value = true
      state.baomingbiao = JSON.parse(JSON.stringify(row))
    }
    function handleDelete() {
      request.delete("/add/" + id).then(res => {
        if (res.code === '200' && res.data === true) {
          ElMessage.success("删除成功！")
          router.push({name: 'FrontPage'})
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
    function Confirm() {
      // 表单验证
      proxy.$refs.ruleFormRef.validate((valid) => {
        if (valid === true) { // 请求后台接口
          request.put("/add", state.baomingbiao).then(res => {
            if (res.code === '200') {
              ElMessage({type: 'success', message: '修改成功！！！'})
              /*关闭弹窗*/
              dialogFormVisible1.value = false
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
      dialogFormVisible1,
      state
    }
  },
};
</script>

<style>
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
  color: brown;
  font-size: 10px;
}
.ml-4 .el-radio__input.is-checked + .el-radio__label{
  color: black;
}
</style>
