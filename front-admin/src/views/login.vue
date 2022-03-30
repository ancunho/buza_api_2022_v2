<template>
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <i class="ace-icon fa fa-leaf green"></i>
                                <span class="red"></span>
                            </h1>
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger"><i
                                            class="ace-icon fa fa-coffee green"></i>Please Enter Your Information</h4>
                                        <div class="space-6"></div>
                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
                          <span class="block input-icon input-icon-right">
                              <input type="text" class="form-control" placeholder="Username" v-model="username"/>
                              <i class="ace-icon fa fa-user"></i>
                          </span>
                                                </label>

                                                <label class="block clearfix">
                          <span class="block input-icon input-icon-right">
                              <input type="password" class="form-control" placeholder="Password" v-model="password"/>
                              <i class="ace-icon fa fa-lock"></i>
                          </span>
                                                </label>

                                                <div class="space"></div>

                                                <label class="block clearfix">
                          <span class="block input-icon input-icon-right">
                            <div class="input-group">
                              <input v-model="captchaCode" type="text" class="form-control" placeholder="验证码">
                              <input v-model="captchaKey" type="hidden" class="form-control">
                              <span class="input-group-addon" id="basic-addon2" style="padding:0;">
                                <img v-on:click="loadCaptchaImage()" id="image-code" alt="验证码" style="height:34px;"/>
                              </span>
                            </div>
                          </span>
                                                </label>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input type="checkbox" class="ace"/>
                                                        <span class="lbl"> Remember Me</span>
                                                    </label>

                                                    <button type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110" v-on:click="login()">Login</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>

                                        <div class="space-6"></div>
                                    </div><!-- /.widget-main -->

                                    <!--                  <div class="toolbar clearfix">-->
                                    <!--                    <div>-->
                                    <!--                      <a href="#" data-target="#forgot-box" class="forgot-password-link">-->
                                    <!--                        <i class="ace-icon fa fa-arrow-left"></i>-->
                                    <!--                        I forgot my password-->
                                    <!--                      </a>-->
                                    <!--                    </div>-->

                                    <!--                    <div>-->
                                    <!--                      <a href="#" data-target="#signup-box" class="user-signup-link">-->
                                    <!--                        I want to register-->
                                    <!--                        <i class="ace-icon fa fa-arrow-right"></i>-->
                                    <!--                      </a>-->
                                    <!--                    </div>-->
                                    <!--                  </div>-->
                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->

                        </div><!-- /.position-relative -->

                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</template>

<script>
export default {
    name: 'login',
    data() {
        return {
            captchaCode: '',
            captchaKey: '',
            username: 'user',
            password: '111',
        }
    },
    mounted() {
        let _this = this;
        $('body').removeClass('no-skin');
        $('body').attr('class', 'login-layout light-login');
        _this.loadCaptchaImage();
    },
    methods: {
        loadCaptchaImage() {
            let _this = this;
            _this.$request.get("/system/captcha").then(response => {
                $("#image-code").attr("src", response.data.data.base64Img);
                _this.captchaKey = response.data.data.captchaKey;
                _this.captchaCode = response.data.data.captchaCode;
            })
        },
        login() {
            event.preventDefault();
            let _this = this;
            if (Tool.isEmpty(_this.username)) {
                _this.$message.error("用户名不能为空");
                return;
            }
            if (Tool.isEmpty(_this.password)) {
                _this.$message.error("密码不能为空");
                return;
            }
            if (Tool.isEmpty(_this.captchaCode)) {
                _this.$message.error("验证码不能为空");
                return;
            }

            _this.$request.post("/system/login?username=" + _this.username + "&password=" + _this.password + "&captchaCode=" + _this.captchaCode + "&captchaKey=" + _this.captchaKey).then(response => {
                if (response.data.status === 200) {
                    Tool.setLoginUser(response.data.data.token);
                    Tool.setStorageParam("username", _this.username);
                    _this.$router.push("/welcome");
                } else {
                    alert(response.data.msg);
                    _this.loadCaptchaImage();
                }
            });

        },
    }
}
</script>
