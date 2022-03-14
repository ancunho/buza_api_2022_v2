import Vue from "vue"
import Router from "vue-router"
import Login from "./views/login.vue"
import Admin_Index from "./views/index.vue"
import Welcome from "./views/admin/welcome.vue"
import System_Menu from "./views/admin/system/page-menu.vue"
import System_Role from "./views/admin/system/page-role.vue"
import System_User from "./views/admin/system/page-user.vue"
import Common_Dict from "./views/admin/system/common-dict.vue"
import Customer_List from "./views/admin/customer/customer-list.vue"
import Shop_List from "./views/admin/shop/shop-list.vue"
import Post_List from "./views/admin/post/post-list.vue"
import Post_Create from "./views/admin/post/post-create.vue"
import Booking_List from "./views/admin/booking/booking-list.vue"
import Room_List from "./views/admin/booking/room-list.vue"
import Booking_Create from "./views/admin/booking/booking-create.vue"
import File_List from "./views/admin/file/file-list.vue"
import Classification_List from "./views/admin/spec/classification-list.vue"
import Spu_List from "./views/admin/spec/spu-list.vue"
import Sku_List from "./views/admin/spec/sku-list.vue"
import Sku_Create from "./views/admin/spec/sku-create.vue"
import Attr_List from "./views/admin/spec/attr-list.vue"

Vue.use(Router);

export default new Router({
    mode: "history",
    // base: process.env.BASE_URL,
    base: '/admin/',
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
        children: [
             { path: "welcome", name: "welcome", component: Welcome, meta: { title: "Dashboard" }}
            ,{ path: "system/user", name: "system/user", component: System_User, meta: { title: "用户管理" }}
            ,{ path: "system/menu", name: "system/menu", component: System_Menu, meta: { title: "菜单管理" }}
            ,{ path: "system/role", name: "system/role", component: System_Role, meta: { title: "权限管理" }}
            ,{ path: "customer/list", name: "customer/list", component: Customer_List, title: "客户管理"}
            ,{ path: "common/dict", name: "common/dict", component: Common_Dict, meta: { title: "数据字典" }}
            ,{ path: "shop/list", name: "shop/list", component: Shop_List, meta: { title: "门店列表" }}
            ,{ path: "post/list", name: "post/list", component: Post_List, meta: { title: "文章列表" }}
            ,{ path: "post/create", name: "post/create", component: Post_Create, meta: {title: "新建/编辑文章" }}
            ,{ path: "booking/list", name: "booking/list", component: Booking_List, meta: {title: "预约列表"}}
            ,{ path: "room/list", name: "room/list", component: Room_List, meta: {title: "Room列表" }}
            ,{ path: "booking/create", name: "booking/create", component: Booking_Create, meta: {title: "新增预约" }}
            ,{ path: "file/list", name: "file/list", component: File_List, meta: {title: "File" }}
            ,{ path: "classification/list", name: "classification/list", component: Classification_List, meta: {title: "Classification" }}
            ,{ path: "spu/list", name: "spu/list", component: Spu_List, meta: {title: "SPU" }}
            ,{ path: "sku/list", name: "sku/list", component: Sku_List, meta: {title: "SKU" }}
            ,{ path: "sku/create", name: "sku/create", component: Sku_Create, meta: {title: "SKU Create" }}
            ,{ path: "attr/list", name: "attr/list", component: Attr_List, meta: {title: "Attribute List" }}
        ]
    }]
})
