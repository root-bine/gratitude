<template>
  <!--添加按钮-->
  <div style="margin-top: 40px; margin-left: 30px">
    <el-input style="width: 260px; margin-right: 10px" v-model="stuName" placeholder="请输入用户名" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="stuID" placeholder="请输入学号" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="profession" placeholder="请输入专业" clearable></el-input>
    <el-button size="default" type="primary" style="margin-left: 10px" @click="load">
      <el-icon style="margin-right: 3px"><Search /></el-icon>查询
    </el-button>
    <el-button size="default" type="danger" style="margin-left: 10px">
      <el-link href="http://localhost:9090/admission/excelDownloads" target="_blank">导出Excel</el-link>
    </el-button>
  </div>

  <el-table :data="state.tableData" stripe
            :header-cell-style="{background:'#9370ad',color:'black',height: '60px'}"
            style="width: 100%;margin-top: 20px;margin-left: 20px" >
    <el-table-column prop="id" label="ID" width="80"/>
    <el-table-column prop="stuID" label="学号" width="140"/>
    <el-table-column prop="stuName" label="姓名" width="90"/>
    <el-table-column prop="profession" label="专业" width="450"/>

    <!--删除按钮-->
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
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
</template>
<script setup>
import {Delete, Search} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import request from "../../utils/request.js";
import {ElMessage} from "element-plus";
const state = reactive({
  tableData: [],
  form: {},
})

// 页面刷新 + 分页刷新
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(0)
const stuName = ref('')
const stuID = ref('')
const profession = ref('')
const load= ()=>{
  request.get("/admission/page", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      stuName: stuName.value,
      stuID: stuID.value,
      profession: profession.value
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

// 删除
const handleDelete= (id)=>{
  request.delete("/admission/"+id).then(res => {
    if(res.code === '200' && res.data === true){
      ElMessage.success("删除成功！")
    }else {
      ElMessage.error(res.msg)
    }
    load()
  })
}
</script>
