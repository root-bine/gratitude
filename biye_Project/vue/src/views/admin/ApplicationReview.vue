<template>
  <!--添加按钮-->
  <div style="margin-top: 20px; margin-left: 30px">
    <el-input style="width: 260px; margin-right: 10px" v-model="stuName" placeholder="请输入用户名" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="stuID" placeholder="请输入学号" clearable></el-input>
    <el-input style="width: 260px; margin-right: 10px" v-model="profession" placeholder="请输入专业" clearable></el-input>
    <el-button size="default" type="primary" style="margin-left: 10px" @click="load">
      <el-icon style="margin-right: 3px"><Search /></el-icon>查询
    </el-button>
  </div>

  <el-table :data="state.tableData" stripe style="width: 95%;margin-top: 10px;margin-left: 20px" >
    <el-table-column prop="id" label="ID" width="60"/>
    <el-table-column prop="stuID" label="学号" width="100"/>
    <el-table-column prop="stuName" label="姓名" width="90"/>
    <el-table-column prop="profession" label="专业" width="90"/>
    <el-table-column prop="school" label="申请学校" width="120"/>
    <el-table-column prop="test" label="综测成绩" width="90"/>
    <el-table-column prop="average" label="平均绩点" width="90"/>
    <el-table-column prop="course" label="挂科/重修" width="120"/>
    <el-table-column prop="myself" label="处分/奖励" width="140"/>
    <el-table-column prop="file" label="文件审核" width="100"/>
    <el-table-column prop="audit" label="申请审核" width="100"/>

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

  <el-dialog v-model="dialogFormVisible" title="编辑信息" width="50%">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="100px">
      <el-form-item label="姓名" prop="stuName">
        <el-input v-model="state.form.stuName" autocomplete="off" disabled style="width: 20%"/>
      </el-form-item>
      <el-form-item label="学号" prop="stuID">
        <el-input v-model="state.form.stuID" autocomplete="off" disabled style="width: 20%"/>
      </el-form-item>
      <el-form-item label="专业" prop="profession">
        <el-input v-model="state.form.profession" autocomplete="off" disabled style="width: 35%"/>
      </el-form-item>
      <el-form-item label="申请学校" prop="school">
        <el-input v-model="state.form.school" autocomplete="off" disabled style="width: 35%"/>
      </el-form-item>
      <el-form-item label="综测成绩" prop="test">
        <el-input v-model="state.form.test" autocomplete="off" disabled style="width: 35%"/>
      </el-form-item>
      <el-form-item label="平均绩点" prop="average">
        <el-input v-model="state.form.average" autocomplete="off" disabled style="width: 35%"/>
      </el-form-item>
      <el-form-item label="专业课情况" prop="course">
        <el-input  type="textarea" v-model="state.form.course" autocomplete="off" disabled style="width: 60%"/>
      </el-form-item>
      <el-form-item label="处分/奖励" prop="myself">
        <el-input  type="textarea" v-model="state.form.myself" autocomplete="off" disabled style="width: 60%"/>
      </el-form-item>

      <el-form-item label="文件审核" prop="file">
        <el-radio-group v-model="state.form.file" class="ml-4">
          <el-radio label="已通过" size="large"></el-radio>
          <el-radio label="未通过" size="large"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="申请结果" prop="audit">
        <el-radio-group v-model="state.form.audit" class="ml-4">
          <el-radio label="已通过" size="large"></el-radio>
          <el-radio label="未通过" size="large"></el-radio>
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
  const dialogFormVisible = ref(false)

  const state = reactive({
    tableData: [],
    form: {},
    rules: {
      file: [
        {required: true, trigger: 'blur'},
      ],
      audit: [
        {required: true, trigger: 'blur'},
      ]
    }
  })

  // 页面刷新 + 分页刷新
  const currentPage = ref(1)
  const pageSize = ref(5)
  const total = ref(0)
  const stuName = ref('')
  const stuID = ref('')
  const profession = ref('')
  const load= ()=>{
    request.get("/apply/page", {
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

  // 编辑
  const handleEdit= (row)=>{
    dialogFormVisible.value = true
    state.form = JSON.parse(JSON.stringify(row))
  }
  // 删除
  const handleDelete= (id)=>{
    request.delete("/apply/list/"+id).then(res => {
      if(res.code === '200' && res.data === true){
        ElMessage.success("删除成功！")
      }else {
        ElMessage.error(res.msg)
      }
      load()
    })
  }
  // 确定选项
  const Confirm= ()=>{
    // 表单验证
    proxy.$refs.ruleFormRef.validate((valid) => {
      if(valid === true){ // 请求后台接口
        if(state.form.id){
          request.put("/apply/list", state.form).then(res => {
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
        }
      }else{
        ElMessage({ type: 'error', message: '操作失败！！！'})
      }
    })
  }
</script>
