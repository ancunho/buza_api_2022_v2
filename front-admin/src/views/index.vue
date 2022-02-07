<template>
  <div>
    <input type="checkbox" name="" id="menu-toggle" />
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
            Good Shot
          </h2>
        </div>
        <div class="sidebar-menu" id="navbar">
          <ul>
            <li class="welcome-sidebar active">
              <router-link to="/welcome">
                <i class="el-icon-s-data" style="font-size: 20px; margin-right: 0.5rem;"></i>
                <span>Dashboard</span>
                <span class="las la-angle-down"></span>
              </router-link>
            </li>
            <li class="system-user-sidebar">
              <router-link to="/system/user">
                <i class="el-icon-s-tools" style="font-size: 20px; margin-right: 0.5rem;"></i>
                <span>系统管理</span>
                <span class="las la-angle-down"></span>
              </router-link>
              <ul class="sidebar-submenu">
                <li class="system-user-sidebar">
                  <router-link to="/system/user">
                    <i class="el-icon-user-solid" style="font-size: 20px; margin-right: 0.5rem;"></i>
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
              </ul>
            </li>

            <li class="shop-list-sidebar">
              <router-link to="/shop/list">
                <i class="el-icon-place" style="font-size: 20px; margin-right: 0.5rem;"></i>
                <span>门店管理</span>
                <span class="las la-angle-down"></span>
              </router-link>
              <ul class="sidebar-submenu">
                <li class="shop-list-sidebar">
                  <router-link to="/shop/list">
                    <i class="el-icon-location-information" style="font-size: 20px; margin-right: 0.5rem;"></i>
                    <span>门店列表</span>
                    <span class="las la-angle-right"></span>
                  </router-link>
                </li>
              </ul>
            </li>

          </ul>
        </div>

<!--        <div class="sidebar-card">-->
<!--          &lt;!&ndash;                <img src="./static/images/main02.png" />&ndash;&gt;-->
<!--          <div class="side-card-icon">-->
<!--            <span class="lab la-codiepie"></span>-->
<!--          </div>-->
<!--          <div>-->
<!--            <h4>Make Adsence</h4>-->
<!--            <p>Add ass foasdklfj</p>-->
<!--          </div>-->
<!--          <button class="btn btn-main btn-block">Create now</button>-->
<!--        </div>-->

      </div>
    </div><!-- //.sidebar -->

    <div class="main-content">
      <header>
        <div class="header-title-wrapper">
          <label for="menu-toggle">
            <span  class="las la-bars"></span>
          </label>
          <div class="header-title">
            <h4>Analytics</h4>
<!--            <p>Display analytics about your Channel <span class="las la-chart-line"></span></p>-->
          </div>

        </div>
        <div class="header-action">
          <button class="btn waves-effect waves-light">
            Upload
          </button>

        </div>
      </header><!-- //.header -->

      <main>
        <router-view />
      </main>

    </div>



  </div><!-- //.wrap div -->
</template>

<script>
export default {
  name: "index",
  mounted: function() {
    let _this = this;
    $('body').attr('class', 'no-skin');
    _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar", _this.$route.meta.title);
  },
  watch: {
    $route: {
      handler: function(val, oldVal) {
        let _this = this;
        console.log(_this.$route);
        _this.$nextTick(function(){
          _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar", _this.$route.meta.title);
        });
      }
    }
  },
  methods: {
    activeSidebar: function(className, title) {
      // console.log(title);
      $(".header-title h1").text(title);
      $(".sidebar-menu li").removeClass("active");
      $("." + className).addClass("active");

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