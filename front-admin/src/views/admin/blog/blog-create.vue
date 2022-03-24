<template>
    <div v-loading="loading">
        <el-form ref="form" :model="form" label-width="120px">
            <input type="hidden" v-model="form.postId" />
            <el-form-item label="文章标题">
                <el-input v-model="form.postTitle"></el-input>
            </el-form-item>
            <el-form-item label="文章类别">
                <el-select v-model="form.postType" placeholder="请选择文章分类">
                    <el-option v-for="item in lstPostType" :label="item.codeName" :key="item.codeId" :value="item.codeCd"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="是否参与">
                <el-switch v-model="form.isJoin"></el-switch>
            </el-form-item>
            <el-form-item label="活动时间" v-if="form.isJoin">
                <el-col :span="10">
                    <el-date-picker
                        v-model="form.eventDateRange"
                        type="daterange"
                        format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-col>
            </el-form-item>
            <el-form-item label="是否需要付款">
                <el-switch v-model="form.isNeedPay"></el-switch>
            </el-form-item>
            <el-form-item label="价格" v-if="form.isNeedPay">
                <el-input v-model="form.postPrice"></el-input>
            </el-form-item>
            <el-form-item label="是否发布">
                <el-radio-group v-model="form.status">
                    <el-radio label="1">是</el-radio>
                    <el-radio label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="文章内容">
                <div id="post-content-div" style="z-index: 1!important;"></div>
                <el-input type="hidden" v-model="form.postContent"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleOnSubmit">立即创建</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
import wangEditor from 'wangeditor'
export default {
    name: "blog-create",

    data() {
        return {
            form: {
                postId: '',
                postTitle: '',
                postContent: '',
                postType: '',
                postAuthor: Tool.getLoginUser(),
                eventStartTime: '',
                eventEndTime: '',
                isJoin: false,
                isNeedPay: false,
                postPrice: 0,
                type: [],
                status: '1',
                eventDateRange: ''
            },
            lstPostType: [],
            editor: null,
            editorData: '',
            loading: true,
        }
    },
    mounted() {
        let _this = this;

        _this.setListCommonCode("POST_TYPE");
        _this.form.postId = _this.$route.params.postId;
        if (Tool.isNotNull(_this.form.postId)) {
            _this.getPostInfoByPostId(_this.form.postId);
        }
        _this.initWangEditor();
        _this.loading = false;
    },
    methods: {
        setListCommonCode(codeType) {
            let _this = this;
            _this.$request.get("/system/code/type/list?codeType=" + codeType).then((response) => {
                _this.lstPostType = response.data.data;
            })
        },
        getPostInfoByPostId(postId) {
            let _this = this;
            _this.$request.get('/system/post/info?postId=' + postId)
            .then(response => {
                if (response.data.code === 0) {
                    _this.form = response.data.data;
                    _this.form.isJoin = _this.form.isJoin === "1";
                    _this.form.isNeedPay = _this.form.isNeedPay === "1";
                    _this.form.eventDateRange = [_this.form.eventStartTime || '', _this.form.eventEndTime || ''];
                    _this.editor.txt.html(_this.form.postContent);
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.loading = false;
            })
            .catch(response => {
                alert("Fail");
            });
        },
        handleOnSubmit() {
            let _this = this;
            _this.form.eventStartTime = _this.form.eventDateRange[0];
            _this.form.eventEndTime = _this.form.eventDateRange[1];
            _this.form.postContent = _this.editorData;
            _this.form.isJoin = _this.form.isJoin == true ? "1" : "0";
            _this.form.isNeedPay = _this.form.isNeedPay == true ? "1" : "0";
            _this.$request.post("/system/post/proc", _this.form)
                .then(response => {
                    if (response.data.code === 0) {
                        _this.$message.success(response.data.msg);
                        _this.$router.push({name: 'post/list'});
                    } else {
                        _this.$message.error(response.data.msg);
                    }
                }).catch(response => {
                    _this.$message.error(response.data.msg);
                });
        },
        initWangEditor() {
            let _this = this;
            const editor = new wangEditor(`#post-content-div`);
            // 配置 onchange 回调函数，将数据同步到 vue 中
            editor.config.onchange = (newHtml) => {
                this.editorData = newHtml
            };
            editor.config.uploadImgServer = '/system/file/handle/upload';
            editor.config.uploadFileName = 'file';
            editor.config.showLinkImg = false;
            editor.config.uploadImgMaxLength = 1;
            // editor.customConfig.uploadImgShowBase64 = true;
            editor.config.uploadImgHooks = {
                fail: function (xhr, editor, result) {
                    _this.$message.error("哎哟,好像出了问题!");
                },
                error: function (xhr, editor) {
                    _this.$message.error("哎哟,好像出了问题!");
                },
                timeout: function (xhr, editor) {
                    _this.$message.error("哎哟,好像出了问题!");
                },
                customerInsert : function(insertImg, result, editor){
                    for (var i = 0; i < result.data.length; i++) {
                        insertImg(result.data[i]);
                    }
                }
            };
            // 创建编辑器
            editor.create();

            this.editor = editor;
        }
    }
}
</script>

<style>
#post-content-div > div {
    z-index: 2000!important;
}
</style>