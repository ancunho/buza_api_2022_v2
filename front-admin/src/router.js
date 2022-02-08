import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin_Index from "./views/index.vue"
import Welcome from "./views/admin/welcome.vue"
import System_Menu from "./views/admin/system/page-menu.vue"
import System_Role from "./views/admin/system/page-role.vue"
import System_User from "./views/admin/system/page-user.vue"
import Customer_List from "./views/admin/customer/customer-list.vue"
import Shop_List from "./views/admin/shop/shop-list.vue"
import Post_List from "./views/admin/post/post-list.vue"

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "",
        redirect: "/login",
        title: "Login"
    }, {
        path: "/login",
        component: Login
    }, {
        path: "/",
        name: "admin",
        component: Admin_Index,
        meta: {
            requireAuth: true
        },
        children: [{
            path: "welcome",
            name: "welcome",
            component: Welcome,
            meta: {
                title: "Dashboard"
            }
        }, {
            path: "system/user",
            name: "system/user",
            component: System_User,
            meta: {
                title: "用户管理"
            }
        }, {
            path: "system/menu",
            name: "system/menu",
            component: System_Menu,
            meta: {
                title: "菜单管理"
            }
        }, {
            path: "system/role",
            name: "system/role",
            component: System_Role,
            meta: {
                title: "权限管理"
            }
        }, {
            path: "customer/list",
            name: "customer/list",
            component: Customer_List,
            title: "客户管理"
        }, {
            path: "shop/list",
            name: "shop/list",
            component: Shop_List,
            meta: {
                title: "门店列表"
            }
        }, {
            path: "post/list",
            name: "post/list",
            component: Post_List,
            meta: {
                title: "文章列表"
            }
        }]
    }]
})
