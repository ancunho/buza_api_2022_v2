<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">上传文件</el-button>

        <el-row :gutter="20">
            <el-col :span="4" v-for="(o, index) in 10" :key="o"  style="margin-bottom: 20px;">
                <el-card :body-style="{ padding: '0px' }">
                    <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
                    <div style="padding: 14px;">
                        <span>好吃的汉堡</span>
                        <div class="bottom clearfix">
                            <time class="time">{{ currentDate }}</time>
                            <el-button type="text" class="button">操作按钮</el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="postTitle" label="postTitle" width="150" ></el-table-column>
            <el-table-column prop="postTypeName" label="postTypeName" ></el-table-column>
            <el-table-column prop="postAuthor" label="postAuthor" ></el-table-column>
            <el-table-column prop="eventStartTime" label="eventStartTime" width="130"></el-table-column>
            <el-table-column prop="eventEndTime" label="eventEndTime" width="130"></el-table-column>
            <el-table-column prop="status" label="状态" align="center" width="120">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.status == '0'"> {{ scope.row.statusName }}</el-tag>
                    <el-tag type="success" v-if="scope.row.status == '1'"> {{ scope.row.statusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
            <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button @click="handleItemModify(scope.$index, scope.row)" type="primary" icon="el-icon-edit-outline">修改门店</el-button>
                    <!--          <el-button @click="getRoleHandler(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>-->
                </template>
            </el-table-column>
        </el-table>
        <!--  // table list end  -->

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

        <!--  dialog start  -->
        <el-dialog v-bind:title="buzaModalTitle" :visible.sync="isModalVisible" :close-on-click-modal="false">
            <!--:close-on-click-modal="false"-->
            <input type="hidden" v-model="modifyItem.postId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="ID" v-if="modifyItem.postId != null">
                    <el-input v-model="modifyItem.postId" disabled></el-input>
                </el-form-item>
                <el-row :gutter="20">
                    <el-col :span="18">
                        <el-form-item label="SPU_NAME">
                            <el-input v-model="modifyItem.desc"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-upload
                            action="#"
                            :before-upload="handleBeforeUpload"
                            list-type="picture-card"
                            :auto-upload="false">
                            <i slot="default" class="el-icon-plus"></i>
                            <div slot="file" slot-scope="{file}">
                                <img
                                    class="el-upload-list__item-thumbnail"
                                    :src="file.url" alt=""
                                >
                                <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                              <i class="el-icon-zoom-in"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                              <i class="el-icon-delete"></i>
                            </span>
                        </span>
                            </div>
                        </el-upload>
                    </el-col>
                </el-row>

                <el-form-item label="是否激活">
                    <el-switch v-model="modifyItem.status"></el-switch>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" v-on:click="isModalVisible = false">取 消</el-button>
                <el-button type="primary" v-on:click="saveItem(modifyItem)">保 存</el-button>
            </div>
        </el-dialog>
        <!--  // dialog end  -->

    </div>
</template>

<script>
export default {
    name: "post-list",
    data: function () {
        return {
            itemList: [],
            modifyItem: {},
            loading: true,
            currentPage: 1, //page
            pageSize: 5, //limit
            pageSizes: [5, 15, 30, 50],
            total: 100,
            isModalVisible: false,
            buzaModalTitle: 'Modal',

            disabled: false,
            currentDate: new Date(),
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
    },
    methods: {
        handleBeforeUpload(file) {
            let _this = this;
            console.log(file);
            console.log(_this.modifyItem);
        },
        handlePictureCardPreview(file) {
            console.log(file);
        },
        handleRemove(file) {
            console.log(file);
        },
        handleSizeChange(limit) {
            this.currentPage = 1;
            this.pageSize = limit;
            this.tableList();
        },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.tableList();
        },
        tableList() {
            let _this = this;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/post/list?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
                _this.itemList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "上传图片";
            _this.modifyItem = {};
        },
        handleItemModify(idx, item) {
            let _this = this;
            _this.$router.push({
                name: 'post/create',
                params: {
                    postId: item.postId
                }
            });
        },
        saveItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";

            console.log(_this);

            // _this.loading = true;
            // _this.$request.post(process.env.VUE_APP_SERVER + "/system/shop/proc", item).then(response => {
            //     if (response.data.code === 0) {
            //         _this.$notify.success(response.data.msg);
            //         _this.tableList();
            //     } else {
            //         _this.$notify.error(response.data.msg);
            //     }
            //     _this.isModalVisible = false;
            // });

        }
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