<template>
  <!--添加按钮-->
  <div style="margin-top: 30px; margin-left: 30px">
    <el-input style="width: 260px; margin-right: 10px" v-model="username" placeholder="请输入用户名" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="studentid" placeholder="请输入学号" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="department" placeholder="请输入院系" clearable></el-input>
    <el-button size="default" type="primary" style="margin-left: 10px" @click="load">
      <el-icon style="margin-right: 3px"><Search /></el-icon>查询
    </el-button>
    <el-button size="default" type="primary" @click="handleAdd">
      <el-icon style="margin-right: 3px"><Plus /></el-icon>新增
    </el-button>
  </div>

  <el-table :data="state.tableData" stripe
            :header-cell-style="{background:'#add8e6',height: '60px'}"
            style="width: 100%;margin-top: 30px;margin-left: 20px" >
    <el-table-column prop="id" label="ID" width="90"/>
    <el-table-column prop="college" label="学校" width="140"/>
    <el-table-column prop="department" label="院系" width="140"/>
    <el-table-column prop="username" label="用户名" width="130"/>
    <el-table-column prop="password" label="密码" width="130"/>
    <el-table-column prop="studentid" label="学号" width="150"/>
    <el-table-column prop="region" label="政治面貌" width="200"/>

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
  <div style="margin: 10px 0;margin-left: 20px">
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

  <!--弹窗界面-->
  <el-dialog v-model="dialogFormVisible1" title="新增信息" width="50%">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="100px">
      <el-form-item label="学校" prop="college">
        <el-input v-model="state.form.college" autocomplete="off" placeholder="请输入学校名称" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="院系" prop="department">
        <el-input v-model="state.form.department" autocomplete="off" placeholder="请输入院系名称" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="学号" prop="studentid">
        <el-input v-model="state.form.studentid" autocomplete="off" placeholder="请输入学号" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="政治面貌" prop="region">
        <el-radio-group v-model="state.form.region" class="ml-4">
          <el-radio label="普通群众" size="large" border></el-radio>
          <el-radio label="共青团员" size="large" border></el-radio>
          <el-radio label="预备党员" size="large" border></el-radio>
          <el-radio label="正式党员" size="large" border></el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
        <el-button type="primary" @click="Confirm">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogFormVisible" title="编辑信息" width="50%">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="100px">
      <el-form-item label="学校" prop="college">
        <el-input v-model="state.form.college" autocomplete="off" placeholder="请输入学校名称" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="院系" prop="department">
        <el-input v-model="state.form.department" autocomplete="off" placeholder="请输入学院名称" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="学号" prop="studentid">
        <el-input v-model="state.form.studentid" autocomplete="off" placeholder="请输入学号" style="width: 20%"/>
      </el-form-item>
      <el-form-item label="政治面貌" prop="region">
        <el-radio-group v-model="state.form.region" class="ml-4">
          <el-radio label="普通群众" size="large" border></el-radio>
          <el-radio label="共青团员" size="large" border></el-radio>
          <el-radio label="预备党员" size="large" border></el-radio>
          <el-radio label="正式党员" size="large" border></el-radio>
        </el-radio-group>
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
import {Edit, Delete,Plus,Search} from "@element-plus/icons-vue";
import {getCurrentInstance, reactive, ref} from "vue";
import request from "../../utils/request.js";
import {ElMessage} from "element-plus";
// 表单校验引入
const {proxy} = getCurrentInstance()
// 弹窗配置
const dialogFormVisible1 = ref(false)
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
    studentid: [
      {required: true, message: '请输入学号', trigger: 'blur'},
      {max:12, message: '最多输入12个字符', trigger: 'blur'}
    ],
    college: [
      {required: true, message: '请输入学校名称', trigger: 'blur'},
    ],
    department: [
      {required: true, message: '请输入学院名称', trigger: 'blur'},
    ],
    region: [
      {required: true, message: '请填写政治面貌', trigger: 'blur'},
      {max:4, message: '最多输入4个字符', trigger: 'blur'}
    ]
  }
})

// 页面刷新 + 分页刷新
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(0)
const username = ref('')
const studentid = ref('')
const department = ref('')
const load= ()=>{
  request.get("/root/page", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      username: username.value,
      studentid: studentid.value,
      department: department.value
    }
  }).then(res => {
    if (res.code === '200') {
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

// 新增
const handleAdd= ()=>{
  dialogFormVisible1.value = true
  state.form = {} //初始化, 避免新增时, 存在已有数据的情况
}
// 编辑
const handleEdit= (row)=>{
  dialogFormVisible.value = true
  state.form = JSON.parse(JSON.stringify(row))
}
// 删除
const handleDelete= (id)=>{
  request.delete("/root/"+id).then(res => {
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
          if(state.form.id){
            request.put("/root", state.form).then(res => {
              if(res.code === '200') {
                ElMessage({ type: 'success', message: '修改成功！！！'})
                /*关闭弹窗*/
                dialogFormVisible.value = false
                /*刷新表格*/
                load()
              }else {
                ElMessage({ type: 'error', message: res.msg})
              }
            })
          }else{
            request.post("/root", state.form).then(res => {
              if(res.code === '200') {
                ElMessage({ type: 'success', message: '添加成功！！！'})
                /*关闭弹窗*/
                dialogFormVisible1.value = false
                /*刷新表格*/
                load()
              } else {
                ElMessage.error(res.msg)
              }
            })
          }
        }else{
          ElMessage({ type: 'error', message: '操作失败！！！'})
        }
      })
    }
</script>