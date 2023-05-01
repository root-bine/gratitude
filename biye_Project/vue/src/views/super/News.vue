<template>
  <!--添加按钮-->
  <h1 style="text-align: center;font-size: 35px;color: coral">公告栏管理</h1>
  <div style="margin-top: 30px; margin-left: 30px">
    <el-input style="width: 260px; margin-right: 10px" v-model="title" placeholder="请输入标题" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="updater" placeholder="请输入作者" clearable></el-input>
    <el-button size="default" type="primary" style="margin-left: 10px" @click="load">
      <el-icon style="margin-right: 3px"><Search /></el-icon>查询
    </el-button>
    <el-button size="default" type="primary" @click="handleAdd">
      <el-icon style="margin-right: 3px"><Plus /></el-icon>新增
    </el-button>
  </div>

  <el-table :data="state.tableData" stripe
            :header-cell-style="{background:'#add8e6',height: '60px'}"
            style="width: 97%;margin-top: 30px;margin-left: 20px" >
    <el-table-column prop="id" label="ID" width="90"/>
    <el-table-column prop="title" label="标题" width="140"/>
    <el-table-column prop="content" label="内容" width="400"/>
    <el-table-column prop="updater" label="作者" width="130"/>
    <el-table-column prop="time" label="发布日期" width="250"/>

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
      <el-row>
        <el-col :span="24">
          <el-form-item label="标题" prop="title">
            <el-input v-model="state.form.title"
                      autocomplete="off" placeholder="请输入公告题目" style="width: 50%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="作者" prop="updater">
            <el-input v-model="state.form.updater" autocomplete="off" placeholder="请输入作者名称" style="width: 20%"/>
          </el-form-item>
        </el-col>
          <el-col :span="24">
            <el-form-item label="内容" prop="content" class="imset">
              <el-input style="min-width: 100px; max-width: 600px;" :rows="30"
                        placeholder="公告内容" v-model="state.form.content"
                        type="textarea" autocomplete="off"/>
            </el-form-item>
          </el-col>
      </el-row>
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
      <el-row>
        <el-col :span="24">
          <el-form-item label="标题" prop="title">
            <el-input v-model="state.form.title"
                      autocomplete="off" placeholder="请输入公告题目" style="width: 50%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="作者" prop="updater">
            <el-input v-model="state.form.updater" autocomplete="off" placeholder="请输入作者名称" style="width: 20%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容" prop="content" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="30"
                      placeholder="公告内容" v-model="state.form.content"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>
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
    title: [
      {required: true, trigger: 'blur'},
    ],
    content: [
      {required: true, trigger: 'blur'},
    ],
    updater: [
      {required: true, trigger: 'blur'},
    ]
  }
})

// 页面刷新 + 分页刷新
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(0)
const title = ref('')
const updater = ref('')
const load= ()=>{
  request.get("/news/page", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      title: title.value,
      updater: updater.value
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
  request.delete("/news/"+id).then(res => {
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
            request.put("/news", state.form).then(res => {
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
            request.post("/news", state.form).then(res => {
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