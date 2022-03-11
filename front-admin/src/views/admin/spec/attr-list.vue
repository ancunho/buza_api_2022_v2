<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">新增ATTR</el-button>
<!--        <router-link to="/sku/create"><el-button type="primary" icon="el-icon-plus">新增SKU</el-button></router-link>-->

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="attrId" label="Id" width="150" ></el-table-column>
            <el-table-column prop="attrName" label="Name" width="150" ></el-table-column>
            <el-table-column prop="attrType" label="Type" width="150" ></el-table-column>
            <el-table-column prop="attrImage" label="Attr Image" width="150" >
                <template slot-scope="scope">
                    <el-image :src="scope.row.attrImage" style="width: 100px; height: 100px;" />
                </template>
            </el-table-column>
            <el-table-column label="lstAttrValue">
                <template slot-scope="scope">
                    <el-tag
                        v-for="item in scope.row.lstAttrValue"
                        :key="item.attrValueId"
                        style="cursor: pointer"
                        @click="handleClickAttrValue(item)"
                        closable>
                        {{item.attrValue}}
                    </el-tag>
                    <el-button class="button-new-tag" size="small" @click="handleAddNewAttrValue(scope.row.attrId)">+ New AttrValue</el-button>
                </template>
            </el-table-column>
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
            <input type="hidden" v-model="modifyItem.attrId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="attrId" v-if="modifyItem.attrId != null">
                    <el-input v-model="modifyItem.attrId" disabled></el-input>
                </el-form-item>
                <el-form-item label="Name">
                    <el-input v-model="modifyItem.attrName"></el-input>
                </el-form-item>
                <el-form-item label="Type">
                    <el-input v-model="modifyItem.attrType"></el-input>
                </el-form-item>
                <el-form-item label="是否激活">
                    <el-switch v-model="modifyItem.status"></el-switch>
                </el-form-item>
                <el-form-item label="主图片">
                    <div class="block">
                        <span class="demonstration"></span>
                        <el-image :src="modifyItem.attrImage || ''" style="width: 200px; height:200px;">
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

        <!--  attr value dialog start  -->
        <el-dialog title="Attr Value" :visible.sync="isModalVisibleForAttrValue" :close-on-click-modal="false">
            <!--:close-on-click-modal="false"-->
            <input type="hidden" v-model="modifyItem2.attrValueId"/>
            <input type="hidden" v-model="modifyItem2.attrId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="attrValueId" v-if="modifyItem2.attrValueId != null">
                    <el-input v-model="modifyItem2.attrValueId" disabled></el-input>
                </el-form-item>
                <el-form-item label="Name">
                    <el-input v-model="modifyItem2.attrValue"></el-input>
                </el-form-item>
                <el-form-item label="Type">
                    <el-input v-model="modifyItem2.attrValueImage"></el-input>
                </el-form-item>
                <el-form-item label="是否激活">
                    <el-switch v-model="modifyItem2.status"></el-switch>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" v-on:click="isModalVisibleForAttrValue = false">取 消</el-button>
                <el-button type="primary" v-on:click="saveAttrValueItem(modifyItem2)">保 存</el-button>
            </div>
        </el-dialog>
        <!--  // attr value end  -->

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
    name: "attr-list",
    components: {
        FileListComponent
    },
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
            modifyItem2: {},
            isModalVisibleForAttrValue: false,
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
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
        handleAddNewAttrValue(attrId){
            let _this = this;
            _this.isModalVisibleForAttrValue = true;
            _this.modifyItem2.attrId = attrId;
        },
        handleClickAttrValue(value) {
            console.log(value);
            let _this = this;
            value.status = value.status === "1";
            _this.isModalVisibleForAttrValue = true;
            _this.modifyItem2 = value;
        },
        childEmitItem(data) {
            let _this = this;
            _this.isDrawerVisible = false;
            if (_this.choosenFlag === 'mainImage01') {
                _this.modifyItem.attrImage = data.fileUrl;
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
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/attr/list?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
                _this.itemList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增ATTR";
            _this.modifyItem = {};
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
                _this.modifyItem.attrImage = '';
            }
        },
        saveItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/attr/proc", item).then(response => {
                if (response.data.code === 0) {
                    _this.$message.success(response.data.msg);
                    _this.tableList();
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.isModalVisible = false;
            });

        },
        saveAttrValueItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/attrValue/proc", item).then(response => {
                if (response.data.code === 0) {
                    _this.$message.success(response.data.msg);
                    _this.tableList();
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.isModalVisibleForAttrValue = false;
            });
        }
    }
}
</script>

<style scoped>
.font100px {font-size: 100px;}
.font200px {font-size: 200px;}
</style>