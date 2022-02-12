<template>
    <el-form ref="form" :model="form" label-width="120px">
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
        <el-form-item label="是否发布">
            <el-radio-group v-model="form.resource">
                <el-radio label="1">是</el-radio>
                <el-radio label="0">否</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="文章内容">
            <div id="demo1"></div>
            <el-input type="textarea" v-model="form.postContent"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import wangEditor from 'wangeditor'
export default {
    name: "post-create",
    data() {
        return {
            form: {
                postTitle: '',
                postContent: '',
                postType: '',
                eventStartTime: '',
                eventEndTime: '',
                isJoin: false,
                type: [],
                resource: '1',
                eventDateRange: ''
            },
            lstPostType: [],
            editor: null,
            editorData: ''
        }
    },
    mounted() {
        const editor = new wangEditor(`#demo1`);
        // 配置 onchange 回调函数，将数据同步到 vue 中
        editor.config.onchange = (newHtml) => {
            this.editorData = newHtml
        };
        editor.config.uploadImgServer = '/system/file/single/upload';
        editor.config.uploadFileName = 'file';
        editor.config.showLinkImg = false;
        // editor.customConfig.uploadImgShowBase64 = true;
        editor.config.uploadImgHooks = {
            fail: function (xhr, editor, result) {
                alert("哎哟,好像出了问题!");
            },
            error: function (xhr, editor) {
                alert("哎哟,好像出了问题!");
            },
            timeout: function (xhr, editor) {
                alert("哎哟,好像出了问题!");
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

        let _this = this;
        _this.getCommonCode("POST_TYPE");
    },
    methods: {
        getCommonCode(codeType) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + "/system/code/type/list?codeType=" + codeType).then((response) => {
                _this.lstPostType = response.data.data;
                console.log(_this.lstPostType);
            })
        },
        onSubmit() {
            this.form.eventStartTime = this.form.eventDateRange[0];
            this.form.eventEndTime = this.form.eventDateRange[1];
            console.log('submit!', this.form);
            console.log('editor:', this.editorData);
        }
    }
}
</script>

<style scoped>

</style>