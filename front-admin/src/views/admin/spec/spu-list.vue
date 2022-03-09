<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">新增SPU</el-button>
<!--        <router-link to="/post/create"><el-button type="primary" icon="el-icon-plus">新增文章</el-button></router-link>-->

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="mainImage01" label="Main Image" width="150" >
                <template slot-scope="scope">
                    <el-image :src="scope.row.mainImage01" style="width: 100px; height: 100px;" />
                </template>
            </el-table-column>
            <el-table-column prop="categoryId" label="CATEGORY_ID" width="150" ></el-table-column>
            <el-table-column prop="brandId" label="BRAND_ID" ></el-table-column>
            <el-table-column prop="spuName" label="SPU_NAME" ></el-table-column>
            <el-table-column prop="spuType" label="SPU_TYPE" width="130"></el-table-column>
            <el-table-column prop="status" label="状态" align="center" width="120">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.status === '0'"> {{ scope.row.statusName }}</el-tag>
                    <el-tag type="success" v-if="scope.row.status === '1'"> {{ scope.row.statusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
            <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button @click="handleItemModify(scope.$index, scope.row)" type="primary" icon="el-icon-edit-outline">修改</el-button>
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
                <el-form-item label="classificationId">
                    <el-input v-model="modifyItem.classificationId"></el-input>
                </el-form-item>
                <el-form-item label="depth">
                    <el-select placeholder="请选择分类" v-model="modifyItem.depth01Id" @change="handleChangeDepth01('1', modifyItem.depth01Id)">
                        <el-option v-for="item in lstClassificationDepth01" :label="item.classificationName" :key="item.classificationId" :value="item.classificationId"></el-option>
                    </el-select>
<!--                    <el-select placeholder="请选择2级分类" v-model="depth02ClassificationId" @change="handleChangeDepth02('2', depth02ClassificationId)">-->
<!--                        <el-option v-for="item in lstClassificationDepth02" :label="item.classificationName" :key="item.classificationId" :value="item.classificationId"></el-option>-->
<!--                    </el-select>-->
<!--                    <el-select placeholder="请选择3级分类" v-model="depth03ClassificationId" @change="handleChangeDepth03('3', depth03ClassificationId)">-->
<!--                        <el-option v-for="item in lstClassificationDepth03" :label="item.classificationName" :key="item.classificationId" :value="item.classificationId"></el-option>-->
<!--                    </el-select>-->
                </el-form-item>
<!--                <el-form-item label="brandId">-->
<!--                    <el-input v-model="modifyItem.brandId"></el-input>-->
<!--                </el-form-item>-->
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
                    <div class="block">
                        <span class="demonstration"></span>
                        <el-image :src="modifyItem.mainImage01 || ''" style="width: 200px; height:200px;">
                            <div slot="error" class="image-slot">
                                <i class="el-icon-picture-outline font200px"></i>
                            </div>
                        </el-image>
                    </div>
                    <el-button type="info" v-on:click="handleChooseMain('mainImage01')">选 择 图 片</el-button>
                    <el-button type="danger" v-on:click="handleDeleteChooseMain('mainImage01')">删 除</el-button>
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

        <!--  // drawer start  -->
        <el-drawer
            v-loading="loadingDrawer"
            title="图片详细信息"
            :visible.sync="isDrawerVisible"
            direction="rtl"
            size="80%"
            :close-on-press-escape="false"
        >
            <FileListComponent @childEmitItem="childEmitItem" />
        </el-drawer>
        <!--  // drawer end  -->

    </div>
</template>

<script>
import FileListComponent from '@/components/FileListComponent';
export default {
    name: "spu-list",
    components: {
        FileListComponent
    },
    data: function () {
        return {
            itemList: [],
            modifyItem: {},
            loading: true,
            currentPage: 1, //page
            pageSize: 20, //limit
            pageSizes: [20, 50, 100],
            total: 100,
            isModalVisible: false,
            buzaModalTitle: 'Modal',
            disable: false,

            imgUploadURL: process.env.VUE_APP_SERVER + '/system/file/handle/single/image/upload',
            imgDeleteURL: process.env.VUE_APP_SERVER + '/system/file/handle/single/image/delete',
            dialogImageUrl: '',
            dialogVisible: false,
            fileList: [],

            loadingDrawer: false,
            isDrawerVisible: false,
            itemDetail: {},
            imgList: [],
            initDataListURL: process.env.VUE_APP_SERVER + '/system/file/handle/list',

            choosenFlag: '',
            lstClassificationDepth01: [],
            lstClassificationDepth02: [],
            lstClassificationDepth03: [],
            depth02ClassificationId: '',
            depth03ClassificationId: '',
            initLstClassificationURL: process.env.VUE_APP_SERVER + '/system/classification/list/byParentClassificationId'
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
        _this.getLstClassificationByParentId(0);
    },
    watch: {
        isModalVisible(val, oldVal) {
            let _this = this;
            if (val === false) {
                _this.tableList();
            }
        }
    },
    methods: {
        handleChangeDepth01(depth, parentClassificationId) {
            let _this = this;
            console.log(depth, parentClassificationId);
            _this.getLstClassificationByParentId(0);
        },
        getLstClassificationByParentId(parentClassificationId) {
            let _this = this;
            _this.$request.post(_this.initLstClassificationURL + '?parentClassificationId=' + parentClassificationId)
            .then(response => {
                if (response.data.status === 200) {
                    _this.lstClassificationDepth01 = response.data.data;
                } else {
                    _this.$message.error(response.data.msg);
                }
            })
            .catch(response => {

            });
        },
        childEmitItem(data) {
            let _this = this;
            _this.isDrawerVisible = false;
            if (_this.choosenFlag === 'mainImage01') {
                _this.modifyItem.mainImage01 = data.fileUrl;
            } else if (_this.choosenFlag === 'mainImage02') {
                _this.modifyItem.mainImage02 = data.fileUrl;
            } else if (_this.choosenFlag === 'mainImage03') {
                _this.modifyItem.mainImage03 = data.fileUrl;
            }
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
                if(response.data.status === 200) {
                    _this.itemList = response.data.data;
                    _this.total = response.data.count;
                } else {
                    _this.$message.error(response.data.msg);
                    _this.$router.push("/login");
                }

                _this.loading = false;
            })
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增SPU";
            _this.modifyItem = {};
            _this.getLstClassificationByParentId(0);
        },
        handleItemModify(idx, item) {
            let _this = this;
            _this.isModalVisible = true;
            item.status = item.status === "1";
            _this.modifyItem = item;
        },
        handleChooseMain(flag) {
            let _this = this;
            // _this.loadingDrawer = true;
            _this.isDrawerVisible = true;
            _this.choosenFlag = flag;
        },
        handleDeleteChooseMain(flag) {
            let _this = this;
            if (flag == 'mainImage01') {
                _this.modifyItem.mainImage01 = '';
            }
        },
        saveItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";
            console.log("item:", item);
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/spu/proc", item).then(response => {
                if (response.data.code === 0) {
                    _this.$message.success(response.data.msg);
                    _this.tableList();
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.isModalVisible = false;
            });

        }
    }
}
</script>

<style scoped>
.font100px {font-size: 100px;}
.font200px {font-size: 200px;}
</style>