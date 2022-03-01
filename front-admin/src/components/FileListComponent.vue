<template>
    <div v-loading="loading">

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
                <el-card :body-style="{ padding: '0px' }">
                    <el-image style="width: 100%; height: 200px" :src="item.fileUrl" fit="contain"></el-image>
                    <div style="padding: 14px;">
                        <span style="font-size: .7rem; height: 30px; line-height: 25px; display: inline-block; overflow-y: hidden">{{ item.fileOriginName }}</span>
                        <div class="bottom clearfix">
                            <!--                                <time class="time">{{ item.fileName }}</time>-->
                            <el-button type="text" class="button" v-on:click="handleEmitItem(item)">查看详细</el-button>
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

    </div>
</template>

<script>
export default {
    name: "FileListComponent",
    data: function () {
        return {
            currentDate: new Date(),
            loading: false,
            currentPage: 1, //page
            pageSize: 24, //limit
            pageSizes: [24, 50, 100, 200],
            total: 100,

            imgDetailURL: process.env.VUE_APP_SERVER + '/system/file/handle/info',
            initDataListURL: process.env.VUE_APP_SERVER + '/system/file/handle/list',
            arrInitData: [],
            fileId: '',
            itemDetail: {},
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
        handleEmitItem(item) {
            let _this = this;
            console.log("item:", item);
            _this.$emit("childEmitItem", item);

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