<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">新增Classification</el-button>
<!--        <router-link to="/post/create"><el-button type="primary" icon="el-icon-plus">新增文章</el-button></router-link>-->

        <!-- 级联面板 start -->
        <el-cascader-panel :options="itemList" :props="{value: 'classificationId', label: 'classificationName', checkStrictly: true}" style="margin-top: 20px">
            <template slot-scope="{ node, data }">
                <span>{{ data.classificationName }}</span>
                <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
            </template>
        </el-cascader-panel>
        <!-- //级联面板 end -->

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="classificationId" label="classificationId" width="150" ></el-table-column>
            <el-table-column prop="parentClassificationId" label="父分类" width="150" >
                <template slot-scope="scope">
                    {{ scope.row.parentClassificationId === 0 ? '-' : scope.row.parentClassificationName }}
                </template>
            </el-table-column>
            <el-table-column prop="classificationName" label="classificationName" ></el-table-column>
            <el-table-column prop="classificationType" label="CLASSIFICATION_TYPE" ></el-table-column>
            <el-table-column prop="sortOrder" label="SORT_ORDER" width="130"></el-table-column>
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
            <input type="hidden" v-model="modifyItem.classificationId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="spuId" v-if="modifyItem.classificationId != null">
                    <el-input v-model="modifyItem.classificationId" disabled></el-input>
                </el-form-item>
                <el-form-item label="Name">
                    <el-input v-model="modifyItem.classificationName"></el-input>
                </el-form-item>
                <el-form-item label="parentId">
                    <el-cascader
                        :value="selectedParentValue"
                        @change="handleChangeCategory"
                        :options="lstCategory"
                        :props="{value: 'classificationId', label: 'classificationName', checkStrictly: true}"
                        :show-all-levels="false"
                        clearable>
                        <template slot-scope="{ node, data }">
                            <span>{{ data.classificationName }}</span>
                            <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
                        </template>
                    </el-cascader>
                </el-form-item>
                <el-form-item label="Type">
                    <el-input v-model="modifyItem.classificationType"></el-input>
                </el-form-item>
                <el-form-item label="SortOrder">
                    <el-input v-model="modifyItem.sortOrder"></el-input>
                </el-form-item>
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
    name: "classification-list",
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

            lstClassificationList: [],
            lstCategory: [],
            selectedParentValue: [],
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
        _this.getAllClassificationList();
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
        handleChangeCategory(value) {
            let _this = this;
            _this.selectedParentValue = value[value.length - 1];
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
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/classification/list?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
                _this.setItemListToTree(response.data.data);
                _this.total = response.data.count;
                _this.loading = false;

                let listToTree = response.data.data;
                let result = [{classificationId: 0, classificationName: '无分类'}];
                listToTree.forEach(item => {
                    delete item.children;
                });

                let map = {};
                listToTree.forEach(item => {
                    map[item.classificationId] = item;
                });
                listToTree.forEach(item => {
                    let parent = map[item.parentClassificationId];
                    if (parent) {
                        (parent.children || (parent.children = [])).push(item);
                    } else {
                        result.push(item);
                    }
                });
                _this.lstCategory = result;
            })
        },
        setItemListToTree(itemList) {
            let _this = this;
            let listToTree = itemList;
            let result = [];
            listToTree.forEach(item => {
                delete item.children;
            });

            let map = {};
            listToTree.forEach(item => {
                map[item.classificationId] = item;
            });
            listToTree.forEach(item => {
                let parent = map[item.parentClassificationId];
                if (parent) {
                    (parent.children || (parent.children = [])).push(item);
                } else {
                    result.push(item);
                }
            });
            _this.itemList = result;
        },
        getAllClassificationList() {
            let _this = this;
            _this.$request.post(process.env.VUE_APP_SERVER + '/system/classification/list', {})
            .then (response => {
                if (response.data.status === 200) {
                    _this.lstClassificationList = response.data.data;
                } else {
                    _this.$message.error(response.data.msg);
                }
            })
            .catch(response => {
                _this.$message.error("出错！！！！");
            });
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增Classification";
            _this.modifyItem = {};
        },
        handleItemModify(idx, item) {
            let _this = this;
            _this.isModalVisible = true;
            item.status = item.status === "1";
            _this.modifyItem = item;
        },
        saveItem(item) {
            let _this = this;
            item.status = item.status === true ? "1" : "0";
            console.log(_this.selectedParentValue);
            item.parentClassificationId = _this.selectedParentValue;
            console.log("item:", item);
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/classification/proc", item).then(response => {
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
</style>