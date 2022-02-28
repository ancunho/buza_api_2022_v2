<template>
    <div v-loading="loading">
        <el-upload
            class="upload-demo"
            ref="upload"
            action="#this"
            :file-list="fileList"
            :limit="5"
            multiple
            :auto-upload="false"
            :on-change="handleOnChange"
            :on-exceed="handleOnExceed"
            :http-request="handleOnUpload"
            >
            <el-button slot="trigger" type="primary">1. 选取文件</el-button>
            <el-button style="margin-left: 10px;" type="success" @click="handleSubmitUpload">2. 上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        <h4 style="margin: 1.5rem 0">asdfasdfasdf</h4>

    </div>
</template>

<script>
export default {
    name: "file-list",
    data: function () {
        return {
            loading: false,

            fileList: [],
            fileData: '', // 文件上传数据（多文件合一）
            imgUploadURL: process.env.VUE_APP_SERVER + '/system/file/handle/multie/image/upload',
            imgDeleteURL: process.env.VUE_APP_SERVER + '/system/file/handle/single/image/delete',
        }
    },
    mounted: function () {
        let _this = this;

    },
    methods: {
        handleOnChange(file, fileList) {
            let _this = this;
            let existsFile = fileList.slice(0, fileList.length - 1).find(f => f.name === file.name);
            if (existsFile) {
                _this.$message.error("当前文件已经存在!");
                fileList.pop();
            }
            _this.fileList = fileList;
        },
        handleOnExceed() {
            this.$message.warning(`当前限制选择 5 个文件`)
        },
        handleOnUpload(file) {
            let _this = this;
            console.log(`handleOnUpload:`,  file);
            _this.fileData.append("imageFiles", file.file);
        },
        handleSubmitUpload() {
            let _this = this;
            console.log("fileList:", _this.fileList);
            const isLt1M = _this.fileList.every(file => file.size / 1024 / 1024 < 1);
            if (!isLt1M) {
                _this.$message.error("请检查，上传文件大小不能超过1MB!");
                return;
            }

            _this.fileData = new FormData();
            _this.$refs.upload.submit();
            _this.fileData.append("authors", Tool.getStorageParam("username"));
            console.log("this fileData:", _this.fileData);
            _this.handleRequest(_this.fileData);
        },
        handleRequest(params) {
            let _this = this;
            var instance_files = _this.$request.create({
                headers: {
                    'content-type': 'multipart/form-data',
                    "Request-Source": "client"
                }
            });
            instance_files
                .post(_this.imgUploadURL, params)
                .then(res => {
                    console.log(res);
                })
                .catch(response => {
                    console.log(response);
                })
        }
    }
}
</script>

<style>
.div-upload div.el-upload { display: block;}
.div-upload div.el-upload-dragger {
    width: 100%;
}
</style>