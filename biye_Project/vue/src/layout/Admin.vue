<template>
  <div>
    <el-container>
      <!--header-->
      <el-header class="el-header">
        <Header></Header>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <!--Aside部分添加的Menu菜单作为一个组件,可以抽成一个独立的vue页面,然后只需要在Aside中引用即可-->
          <Menu></Menu>
        </el-aside>

        <el-main class="el-main">
          <!--router-view 当你的路由path 与访问的地址相符时，会将指定的组件替换该 router-view-->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import Menu from "../components/Admin-M.vue";
import Header from "../components/Admin-H.vue";
</script>

<style scoped>
.el-row {
  align-items: center;
  vertical-align: middle;
  padding-top: 0px;
}
.el-header {
  background-color: darkcyan;
  line-height: 50px;
  height: 80px;
}
.el-main {
  padding: 0 !important;
}

</style>

<style>
html,body{
  margin:0;
  padding:0;
}
</style>
