<template>
    <div>
        <input type="checkbox" name="" id="menu-toggle"/>
        <div class="overlay">
            <label for="menu-toggle">
                <span class="las la-cancel"></span>
            </label>
        </div>

        <div class="sidebar">

            <div class="sidebar-container">
                <div class="brand">
                    <h2>
                        <span class="lab la-staylinked"></span>
                        Cunho
                    </h2>
                </div>
                <div class="block" style="display: flex; justify-content: center; margin: 1.2rem 0 .6rem 0">
                    <el-avatar :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
                </div>
                <p style="text-align: center; margin: 0 0 1.2rem 0; font-weight: 600;">{{ username }}</p>
                <div class="sidebar-menu" id="navbar">
                    <ul>
                        <!-- Dashboard start -->
                        <li class="welcome-sidebar active">
                            <router-link to="/welcome">
                                <i class="el-icon-s-data" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>Dashboard</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                        </li>
                        <!-- // Dashboard end -->

                        <!-- 系统管理 start -->
                        <li class="system-user-sidebar">
                            <router-link to="/system/user">
                                <i class="el-icon-s-tools" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>系统管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="system-user-sidebar">
                                    <router-link to="/system/user">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>用户管理</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="system-role-sidebar">
                                    <router-link to="/system/role">
                                        <i class="el-icon-unlock" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>权限管理</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="system-menu-sidebar">
                                    <router-link to="/system/menu">
                                        <i class="el-icon-menu" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>菜单管理</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="common-dict-sidebar">
                                    <router-link to="/common/dict">
                                        <i class="el-icon-paperclip" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>数据字典</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 系统管理 end -->

                        <!-- 文件管理 start -->
                        <li class="file-list-sidebar">
                            <router-link to="/file/list">
                                <i class="el-icon-s-tools" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>文件管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="file-list-sidebar">
                                    <router-link to="/file/list">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>文件列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 规格管理 end -->

                        <!-- 规格管理 start -->
                        <li class="spu-list-sidebar">
                            <router-link to="/spu/list">
                                <i class="el-icon-s-tools" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>规格管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="classification-list-sidebar">
                                    <router-link to="/classification/list">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>Classification</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="spu-list-sidebar">
                                    <router-link to="/spu/list">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>SPU</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="sku-list-sidebar">
                                    <router-link to="/sku/list">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>SKU</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="attr-list-sidebar">
                                    <router-link to="/attr/list">
                                        <i class="el-icon-user-solid"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>Attr</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 规格管理 end -->

                        <!-- 门店管理 start -->
                        <li class="shop-list-sidebar">
                            <router-link to="/shop/list">
                                <i class="el-icon-place" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>门店管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="shop-list-sidebar">
                                    <router-link to="/shop/list">
                                        <i class="el-icon-location-information"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>门店列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="room-list-sidebar">
                                    <router-link to="/room/list">
                                        <i class="el-icon-location-information"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>Room列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 门店管理 end -->

                        <!-- 预约管理 start -->
                        <li class="booking-list-sidebar">
                            <router-link to="/booking/list">
                                <i class="el-icon-place" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>预约管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="booking-create-sidebar">
                                    <router-link to="/booking/create">
                                        <i class="el-icon-location-information"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>新建预约</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="booking-list-sidebar">
                                    <router-link to="/booking/list">
                                        <i class="el-icon-location-information"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>预约列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 预约管理 end -->

                        <!-- 文章管理 start -->
                        <li class="post-list-sidebar">
                            <router-link to="/post/list">
                                <i class="el-icon-document-copy" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>文章管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="post-create-sidebar">
                                    <router-link to="/post/create">
                                        <i class="el-icon-edit"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>新建/编辑</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="post-list-sidebar">
                                    <router-link to="/post/list">
                                        <i class="el-icon-document"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>文章列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 文章管理 end -->

                        <!-- 博客管理 start -->
                        <li class="blog-list-sidebar">
                            <router-link to="/blog/list">
                                <i class="el-icon-document-copy" style="font-size: 20px; margin-right: 0.5rem;"></i>
                                <span>博客管理</span>
                                <span class="las la-angle-down"></span>
                            </router-link>
                            <ul class="sidebar-submenu">
                                <li class="blog-create-sidebar">
                                    <router-link to="/blog/create">
                                        <i class="el-icon-edit"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>新建/编辑</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                                <li class="blog-list-sidebar">
                                    <router-link to="/blog/list">
                                        <i class="el-icon-document"
                                           style="font-size: 20px; margin-right: 0.5rem;"></i>
                                        <span>文章列表</span>
                                        <span class="las la-angle-right"></span>
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <!-- // 博客管理 end -->

                    </ul>
                </div>

            </div>
        </div><!-- //.sidebar -->

        <div class="main-content">
            <header>
                <div class="header-title-wrapper">
                    <label for="menu-toggle">
                        <span class="las la-bars"></span>
                    </label>
                    <div class="header-title">
                        <h2>Analytics</h2>
                        <!--            <p>Display analytics about your Channel <span class="las la-chart-line"></span></p>-->
                    </div>

                </div>
                <div class="header-action">
                    <el-button type="primary" plain icon="el-icon-date" circle></el-button>
                    <el-button type="primary" plain icon="el-icon-switch-button" circle></el-button>
                </div>
            </header><!-- //.header -->

            <main>
                <router-view/>
            </main>

        </div>

    </div><!-- //.wrap div -->
</template>

<script>
export default {
    name: "index",
    data() {
        return {
            username: '',
            isCollapse: false,
        }
    },
    mounted: function () {
        let _this = this;
        _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar", _this.$route.meta.title);
        _this.username = Tool.getStorageParam("username");
    },
    watch: {
        $route: {
            handler: function (val, oldVal) {
                let _this = this;
                _this.$nextTick(function () {
                    _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar", _this.$route.meta.title);
                });
            }
        }
    },
    methods: {
        activeSidebar: function (className, title) {
            console.log(className, title);
            $(".header-title h2").text(title);
            $(".sidebar-menu li").removeClass("active");
            $("." + className).siblings("li").find("ul.sidebar-submenu").css({ 'height': '0px', 'overflow': 'hidden', });
            $("." + className).addClass("active");
            $("." + className).find("ul.sidebar-submenu").css({ 'height': '100%',});
            let parentLi = $("." + className).parents("li");
            if (parentLi) {
                parentLi.addClass("active");
            }
        },
    }
}


</script>

<style scoped>

</style>