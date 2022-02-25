<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">新增文章</el-button>
<!--        <router-link to="/post/create"><el-button type="primary" icon="el-icon-plus">新增文章</el-button></router-link>-->

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="categoryId" label="CATEGORY_ID" width="150" ></el-table-column>
            <el-table-column prop="brandId" label="BRAND_ID" ></el-table-column>
            <el-table-column prop="spuName" label="SPU_NAME" ></el-table-column>
            <el-table-column prop="spuType" label="SPU_TYPE" width="130"></el-table-column>
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
            <input type="hidden" v-model="modifyItem.spuId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="spuId" v-if="modifyItem.spuId != null">
                    <el-input v-model="modifyItem.spuId" disabled></el-input>
                </el-form-item>
                <el-form-item label="categoryId">
                    <el-input v-model="modifyItem.categoryId"></el-input>
                </el-form-item>
                <el-form-item label="brandId">
                    <el-input v-model="modifyItem.brandId"></el-input>
                </el-form-item>
                <el-form-item label="SPU_NAME">
                    <el-input v-model="modifyItem.spuName"></el-input>
                </el-form-item>
                <el-form-item label="SPU_TYPE">
                    <el-input v-model="modifyItem.spuType"></el-input>
                </el-form-item>
                <el-form-item label="是否激活">
                    <el-switch v-model="modifyItem.status"></el-switch>
                </el-form-item>
                <el-form-item label="主图片">
                    <el-upload
                        :action="imgUploadURL"
                        multiple
                        :limit="2"
                        list-type="picture-card"
                        :on-success="handleImageSubmitSuccess"
                        :auto-upload="true">
                        <i slot="default" class="el-icon-plus"></i>
                        <div slot="file" slot-scope="{file}">
                            <el-image :src="file.url" alt="" style="width: 148px; height: 148px"></el-image>
                            <span class="el-upload-list__item-actions">
                                <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                  <i class="el-icon-zoom-in"></i>
                                </span>
                            </span>
                        </div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" v-on:click="isModalVisible = false">取 消</el-button>
                <el-button type="primary" v-on:click="saveItem(modifyItem)">保 存</el-button>
            </div>
        </el-dialog>
        <!--  // dialog end  -->

        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>

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

            imgUploadURL: process.env.VUE_APP_SERVER + '/system/upload/image',
            imgDeleteURL: process.env.VUE_APP_SERVER + '/system/upload/image/delete',
            dialogImageUrl: '',
            dialogVisible: false,
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
    },
    methods: {
        handleImageSubmitSuccess(res, file) {
            console.log("handleImageSubmitSuccess start");
            console.log(res);
            console.log(file);
            console.log("handleImageSubmitSuccess end");
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
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
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/spu/list?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
                _this.itemList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增SPU";
            _this.modifyItem = {};
        },
        handleItemModify(idx, item) {
            let _this = this;
            _this.isModalVisible = true;
            item.status = item.status == "1" ? true : false;
            _this.modifyItem = item;
        },
        saveItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/spu/proc", item).then(response => {
                if (response.data.code === 0) {
                    _this.$notify.success(response.data.msg);
                    _this.tableList();
                } else {
                    _this.$notify.error(response.data.msg);
                }
                _this.isModalVisible = false;
            });

        }
    }
}
</script>

<style scoped>

</style>