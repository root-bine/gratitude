<template>
  <div style="background-color: lightblue;height: 100%">
    <!--添加按钮-->
    <h1 style="text-align: center;font-size: 35px;color: darkolivegreen;margin-top: 0px">推免研究生系统公告</h1>
    <h2 style="margin-left: 50px;color: red">注意: </h2>
    <h3 style="margin-left: 50px">
      1、注意通知的时间节点<br>
      2、综合信息公示请前往学校官网查看<br>
    </h3>
    <el-table :data="state.tableData" stripe
              :header-cell-style="{background:'burlywood',height: '60px'}"
              style="width: 97%;margin-top: 30px;margin-left: 20px" >
      <el-table-column prop="id" width="90"/>
      <el-table-column prop="title" width="350"/>
      <el-table-column prop="updater" width="250"/>
      <el-table-column prop="time" width="250"/>

      <!--修改和删除按钮-->
      <el-table-column fixed="right">
        <template #default="scope">
          <el-button size="small" type="success" @click="handleEdit(scope.row)">
            <!--设置按钮大小、图标与字体间距-->
            <el-icon size="20" style="margin-right: 3px"><Edit /></el-icon>详情
          </el-button>
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
  </div>

  <el-dialog v-model="dialogFormVisible" title="公告详情" width="50%"
              style="background-color: burlywood">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="100px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="标题" prop="title">
            <el-input v-model="state.form.title"
                      autocomplete="off" disabled style="width: 50%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="作者" prop="updater">
            <el-input v-model="state.form.updater" autocomplete="off"
                      disabled style="width: 20%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容" prop="content" class="imset">
            <el-input style="min-width: 100px; max-width: 600px;" :rows="30"
                      disabled v-model="state.form.content"
                      type="textarea" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false" style="text-align: center">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {Edit} from "@element-plus/icons-vue";
import {getCurrentInstance, reactive, ref} from "vue";
import request from "../../utils/request.js";
// 表单校验引入
const {proxy} = getCurrentInstance()
// 弹窗配置
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
const load= ()=>{
  request.get("/news/page", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
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

// 编辑
const handleEdit= (row)=>{
  dialogFormVisible.value = true
  state.form = JSON.parse(JSON.stringify(row))
}
</script>