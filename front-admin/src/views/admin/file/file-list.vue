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

        <!--  paging start  -->
        <el-row style="margin: 2rem 0;">
            <el-col :span="12" :offset="6">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </el-col>
        </el-row>
        <!--  // paging end  -->

        <!--  paging start  -->
        <el-row :gutter="20">
            <el-col :lg="4" :xs="12" :sm="6" :md="6" v-for="item in arrInitData" :key="item.fileId" style="margin-bottom: 20px;">
                <el-card :body-style="{ padding: '0px' }" shadow="hover">
                    <el-image style="width: 100%; height: 200px" :src="item.fileUrl" fit="contain"></el-image>
                    <div style="padding: 14px;">
                        <span style="font-size: .7rem; height: 30px; line-height: 25px; display: inline-block; overflow-y: hidden">{{ item.fileOriginName }}</span>
                        <div class="bottom clearfix">
                            <!--                                <time class="time">{{ item.fileName }}</time>-->
                            <el-button type="text" class="button" v-on:click="handleOpenDrawer(item.fileId)">查看详细</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!--  paging start  -->
        <el-row style="margin: 2rem 0;">
            <el-col :span="12" :offset="6">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </el-col>
        </el-row>
        <!--  // paging end  -->

        <!--  // drawer start  -->
        <el-drawer
            v-loading="loadingDrawer"
            title="图片详细信息"
            :visible.sync="isDrawerVisible"
            direction="rtl"
            size="50%"
            :close-on-press-escape="false"
        >
            <p>fileId : {{ itemDetail.fileId }}</p>
            <p>fileType : {{ itemDetail.fileType || '' }}</p>
            <p>filePurpose : {{ itemDetail.filePurpose || '-' }}</p>
            <p>fileOriginName : {{ itemDetail.fileOriginName }}</p>
            <p>fileName : {{ itemDetail.fileName }}</p>
            <p>filePath : {{ itemDetail.filePath }}</p>
            <p>fileBucketName : {{ itemDetail.fileBucketName }}</p>
            <p>fileBucketObject : {{ itemDetail.fileBucketObject }}</p>
            <p>fileSize : {{ itemDetail.fileSize }}</p>
            <p>status : {{ itemDetail.status }}</p>
            <p>fileExtention : {{ itemDetail.fileExtention }}</p>
            <p>fileUrl : {{ itemDetail.fileUrl }}</p>
        </el-drawer>
        <!--  // drawer end  -->

    </div>
</template>

<script>
export default {
    name: "file-list",
    data: function () {
        return {
            currentDate: new Date(),
            loading: false,
            currentPage: 1, //page
            pageSize: 24, //limit
            pageSizes: [24, 50, 100, 200],
            total: 100,

            fileList: [],
            fileData: '', // 文件上传数据（多文件合一）
            imgUploadURL: process.env.VUE_APP_SERVER + '/system/file/handle/multie/image/upload',
            imgDeleteURL: process.env.VUE_APP_SERVER + '/system/file/handle/single/image/delete',
            imgDetailURL: process.env.VUE_APP_SERVER + '/system/file/handle/info',
            initDataListURL: process.env.VUE_APP_SERVER + '/system/file/handle/list',
            arrInitData: [],
            isDrawerVisible: false,
            fileId: '',
            itemDetail: {},
            loadingDrawer: false,
        }
    },
    mounted: function () {
        let _this = this;
        _this.initDataList();
    },
    methods: {
        initDataList() {
            let _this = this;
            let params = {

            };

            _this.$request
                .post(_this.initDataListURL + '?page=' + _this.currentPage + "&limit=" + _this.pageSize,params)
                .then(res => {
                    if (res.data.status === 200) {
                        _this.arrInitData = res.data.data;
                        _this.total = res.data.count;
                    } else {
                        _this.$message.error(res.data.msg);
                    }
                    _this.loading = false;
                })
                .catch(res => {
                    console.log(res);
                    _this.$message.error("Fail!!!!!!")
                    _this.loading = false;
                });
        },
        handleSizeChange(limit) {
            this.currentPage = 1;
            this.pageSize = limit;
            this.initDataList();
        },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.initDataList();
        },
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
            _this.fileData.append("imageFiles", file.file);
        },
        handleSubmitUpload() {
            let _this = this;
            const isLt1M = _this.fileList.every(file => file.size / 1024 / 1024 < 1);
            if (!isLt1M) {
                _this.$message.error("请检查，上传文件大小不能超过1MB!");
                return;
            }

            _this.fileData = new FormData();
            _this.$refs.upload.submit();
            _this.fileData.append("authors", Tool.getStorageParam("username"));
            _this.handleRequest(_this.fileData);
        },
        handleRequest(params) {
            let _this = this;
            _this.loading = true;
            var instance_files = _this.$request.create({
                headers: {
                    'content-type': 'multipart/form-data',
                    "Request-Source": "client"
                }
            });
            instance_files
                .post(_this.imgUploadURL, params)
                .then(res => {
                    if (res.data.code === 0) {
                        _this.$message.success(res.data.msg);
                        _this.fileList = [];
                    } else {
                        _this.$message.error(res.data.msg);
                    }
                    _this.initDataList();
                })
                .catch(response => {
                    _this.$message.error("上传失败， 请重试或联系管理员");
                    _this.loading = false;
                });
        },
        handleOpenDrawer(fileId) {
            let _this = this;
            _this.fileId = fileId;
            _this.isDrawerVisible = true;
            _this.loadingDrawer = true;
            _this.$request.get(_this.imgDetailURL + '?fileId=' + _this.fileId)
            .then(res => {
                _this.loadingDrawer = false;
                if (res.data.status === 200) {
                    _this.itemDetail = res.data.data;
                } else {
                    _this.$message.error(res.data.msg);
                }

            })
            .catch(res => {

            });

        },
    }
}
</script>

<style scoped>
.time {
    font-size: 13px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.image {
    width: 100%;
    display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>