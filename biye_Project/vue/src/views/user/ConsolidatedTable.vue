<template>
  <el-table :data="state.tableData" stripe style="width: 100%;margin-top: 30px;margin-left: 30px" >
<!--    <el-table-column type="selection" width="55"/>-->
    <el-table-column prop="id" label="ID" width="100"/>
    <el-table-column prop="stuID" label="学号" width="120"/>
    <el-table-column prop="stuName" label="姓名" width="120"/>
    <el-table-column prop="sex" label="性别" width="120"/>
    <el-table-column prop="profession" label="专业" width="120"/>
    <el-table-column prop="english" label="四六级" width="120"/>
    <el-table-column prop="average" label="平均绩点" width="140"/>
    <el-table-column prop="stuStatus" label="政治面貌" width="140"/>

    <!--修改和删除按钮-->
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button size="small" type="success" @click="handleEdit(scope.row)">
          <!--设置按钮大小、图标与字体间距-->
          <el-icon size="20" style="margin-right: 3px"><Edit /></el-icon>编辑
        </el-button>
        <el-popconfirm title="是否要删除此条信息?" confirm-button-text="确定"
                       cancel-button-text="取消" @confirm="handleDelete(scope.row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              <el-icon size="20" style="margin-right: 3px"><Delete/></el-icon>删除
            </el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!--分页配置-->
  <div style="margin: 10px 0;margin-left: 35px">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 15, 25]"
        :small="large"
        :disabled="false"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

  <el-dialog v-model="dialogFormVisible" title="编辑信息" width="50%">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="100px">
      <el-form-item label="学号" prop="stuID">
        <el-input v-model="state.form.stuID" autocomplete="off" placeholder="请输入学号" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="姓名" prop="stuName">
        <el-input v-model="state.form.stuName" autocomplete="off" placeholder="请输入姓名" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="state.form.sex" class="ml-4">
          <el-radio label="男" size="large"/>
          <el-radio label="女" size="large"/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="专业" prop="profession">
        <el-input v-model="state.form.profession" autocomplete="off" placeholder="请填写专业" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="四/六级" prop="english" class="imset">
        <el-radio-group v-model="state.form.english" class="ml-4">
          <el-radio label="ETC-4" size="default" border/>
          <el-radio label="ETC-6" size="default" border/>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="平均绩点" prop="average">
        <el-input v-model="state.form.english" autocomplete="off" placeholder="请填写三年平均绩点" style="width: 35%"/>
      </el-form-item>
      <el-form-item label="政治面貌" prop="stuStatus" class="imset">
        <el-select v-model="state.form.stuStatus" placeholder="请选择" size="large">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="Confirm">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {Edit, Delete} from "@element-plus/icons-vue";
import {getCurrentInstance, reactive, ref} from "vue";
import request from "../../utils/request.js";
import {ElMessage} from "element-plus";
// 表单校验引入
const {proxy} = getCurrentInstance()
// 弹窗配置
const dialogFormVisible = ref(false)

const state = reactive({
  tableData: [],
  form: {},
  rules: {
    /*此处校验: 用户名、密码、地址*/
    username: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
      {min:3, message: '密码不能少于3位', trigger: 'blur'}
    ],
    address: [
      {required: true, message: '请输入地址', trigger: 'blur'},
      {max:255, message: '最多输入255个字符', trigger: 'blur'}
    ]
  }
})
const options = [
  {
    value: '1',
    label: '正式党员',
  },
  {
    value: '2',
    label: '预备党员',
  },
  {
    value: '3',
    label: '共青团员',
  },
  {
    value: '4',
    label: '普通群众'
  }
]
// 页面刷新 + 分页刷新
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(0)
const username = ref('')
const phone = ref('')
const email = ref('')
/* const load=() =>{
   request.get("/users/page").then(res => {
     state.tableData = res.data.list
   })
 }*/
const load= ()=>{
  request.get("/users/page", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      username: username.value,
      phone: phone.value,
      email: email.value
    }
  }).then(res => {
    if (res.code === '200') {
      /**
       * 使用PageHelper数据组成结构变化
       *    原版: data[total, data]
       *    修改: data[total, list]
       */
      /* state.tableData = res.data.data*/
      state.tableData = res.data.list
      total.value = res.data.total
    }
  })
}
load()
const handleSizeChange = (val)=>{
  pageSize.value = val
  load()
}
const handleCurrentChange = (val)=>{
  currentPage.value = val
  load()
}

// 编辑
const handleEdit= (row)=>{
  dialogFormVisible.value = true
  state.form = JSON.parse(JSON.stringify(row))
}
// 删除
const handleDelete= (id)=>{
  request.delete("/users/"+id).then(res => {
    if(res.code === '200' && res.data === true){
      ElMessage.success("删除成功！")
    }else {
      ElMessage.error(res.msg)
    }
    load()
  })
}
/**
 * 需要区分新增、编辑时, 按钮的后端接口判断
 * 编辑与新增的区别在于: id
 * @constructor
 */
    // 确定选项
const Confirm= ()=>{
      // 表单验证
      proxy.$refs.ruleFormRef.validate((valid) => {
        if(valid === true){ // 请求后台接口
          if(state.form.id) {
            request.put("/users", state.form).then(res => {
              if (res.code === '200') {
                ElMessage({type: 'success', message: '修改成功！！！'})
                /*关闭弹窗*/
                dialogFormVisible.value = false
                /*刷新表格*/
                load()
              } else {
                ElMessage({type: 'error', message: res.msg})
              }
            })
          }
        }else{
          ElMessage({ type: 'error', message: '操作失败！！！'})
        }
      })
    }
</script>

<style>
.imset .el-form-item__label{
  color: coral;
}
.ml-4 .el-radio__input.is-checked + .el-radio__label{
  color: black;
}
</style>