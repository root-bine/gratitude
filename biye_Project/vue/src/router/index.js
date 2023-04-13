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
              component: () => import('../views/user/WaiverApplication.vue')
            },
            {
                path: 'application',
                name: 'ApplicationForm',
                component:()  => import('../views/user/ApplicationForm.vue'),
                meta: { title: "推免申请表" }
            },
            {
                path: 'console',
                name: 'ConsolidatedTable',
                component:()  => import('../views/user/ConsolidatedTable.vue'),
                meta: { title: "综合信息表" }
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
        ]
    },
]

const router = createRouter({
    history: createWebHistory("/"),
    routes: routers
})
/**
 * 输出对象
 */
export default router;