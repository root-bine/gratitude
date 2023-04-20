import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
// 路由配置
const routers = [
    {
        path: '/:id',
        name: 'Layout',
        component:() => import('../layout/Layout.vue'),
        meta: { title: "页面布局" },
        redirect: '/login',
        children: [
            {
                path: '/:id/front',
                name: 'FrontPage',
                component:() => import('../views/user/FrontPage.vue'),
                meta: { title: "主页演示" }
            },
            {
                path: 'waiver',
                name: 'WaiverApplication',
                component: () => import('../views/user/WaiverApplication.vue'),
                meta: {title: "推免申请表"}
            },
            {
                path: 'application',
                name: 'ApplicationForm',
                component:()  => import('../views/user/ApplicationForm.vue'),
                meta: { title: "推免报名表" }
            },
            {
                path: 'console',
                name: 'ConsolidatedTable',
                component:()  => import('../views/user/ConsolidatedTable.vue'),
                meta: { title: "报名信息表" }
            },
            {
                path:'new',
                name:'NewPassword',
                component:() => import('../views/user/NewPassword.vue'),
                meta: { title: "修改密码" }
            },
            {
                path:'person',
                name:'Person',
                component:() => import('../views/user/Person.vue'),
                meta: { title: "个人信息" }
            },
            {
                path:'editPerson',
                name:'EditPerson',
                component:() => import('../views/user/EditPerson.vue'),
                meta: { title: "修改个人信息" }
            },
            {
                path:'logo',
                name:'WaiverLgo',
                component:() => import('../views/user/WaiverLgo.vue'),
                meta: { title: "修改申请记录" }
            },
            {
                path:'searchWaiver',
                name:'SearchWaiver',
                component:() => import('../views/user/SearchWaiver.vue'),
                meta: { title: "修改申请记录" }
            },
            {
                path:'searchApplication',
                name:'SearchApplication',
                component:() => import('../views/user/SearchApplication.vue'),
                meta: { title: "修改申请记录" }
            },
            {
                path:'resultWa',
                name:'ResultWa',
                component:() => import('../views/user/ResultWa.vue'),
                meta: { title: "修改申请记录" }
            },
            {
                path:'resultAp',
                name:'ResultAp',
                component:() => import('../views/user/ResultAp.vue'),
                meta: { title: "修改申请记录" }
            },
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component:() => import('../views/Login.vue'),
        meta: { title: "登录页面" }
    },
    {
        path: '/register',
        name: 'Register',
        component:() => import('../views/Register.vue'),
        meta: { title: "注册页面" }
    },
    {
        path: '/',
        name: 'Admin',
        component:() => import('../layout/Admin.vue'),
        meta: { title: "页面布局" },
        redirect: '/login',
        children: [
            {
                path: 'adminF',
                name: 'AdminPag',
                component:() => import('../views/admin/AdminPage.vue'),
                meta: { title: "主页演示" }
            },
            {
                path:'new',
                name:'NewPassword',
                component:() => import('../views/admin/NewPassword.vue'),
                meta: { title: "修改密码" }
            },
            {
                path:'userList',
                name:'UserList',
                component:() => import('../views/admin/UserList.vue'),
                meta: { title: "学生信息列表" }
            },
            {
                path:'applicationReview',
                name:'ApplicationReview',
                component:() => import('../views/admin/ApplicationReview.vue'),
                meta: { title: "申请审核" }
            },
        ]
    },
    {
        path: '/',
        name: 'inLogin',
        redirect: '/Login',
        meta: {title: "在分权后, 强制进入登陆界面"}
    }
]

const router = createRouter({
    history: createWebHistory("/"),
    routes: routers
})
/**
 * 输出对象
 */
export default router;