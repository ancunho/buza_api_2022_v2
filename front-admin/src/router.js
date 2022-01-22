import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin_Index from "./views/index.vue"
import Welcome from "./views/admin/welcome.vue"
import System_Menu from "./views/admin/system/page-menu.vue"
import System_Role from "./views/admin/system/page-role.vue"
import System_User from "./views/admin/system/page-user.vue"

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
        path: "",
        redirect: "/login"
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
            component: Welcome
        }, {
            path: "system/user",
            name: "system/user",
            component: System_User
        }, {
            path: "system/menu",
            name: "system/menu",
            component: System_Menu
        }, {
            path: "system/role",
            name: "system/role",
            component: System_Role
        }]
    }]
})
