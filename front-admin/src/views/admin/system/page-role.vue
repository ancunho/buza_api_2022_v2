<template>
    <div v-loading="loading">

        <el-button @click="addNewRoleHandler" type="primary" icon="el-icon-plus">新增权限</el-button>

        <!--  table list start  -->
        <el-table :data="roleList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="name" label="权限名" width="150"></el-table-column>
            <el-table-column prop="code" label="Code" width="150"></el-table-column>
            <el-table-column prop="remark" label="备注" width="250"></el-table-column>
            <el-table-column prop="status" label="状态" align="center" width="120">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.status == '0'"> {{ scope.row.statusName }}</el-tag>
                    <el-tag type="success" v-if="scope.row.status == '1'"> {{ scope.row.statusName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createtime" label="创建时间" width="220"></el-table-column>
            <el-table-column prop="updatetime" label="更新时间" width="220"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button @click="modifyRoleHandler(scope.row)" type="primary" icon="el-icon-edit-outline">修改权限
                    </el-button>
                    <el-button @click="getRoleMenuHandler(scope.row)" type="info" icon="el-icon-lock">编辑菜单</el-button>
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
            <div v-if="modalType == 1">
                <input type="hidden" v-model="modifyRoleItem.id"/>
                <el-form ref="form" label-width="80px">
                    <el-form-item label="ID" v-if="modifyRoleItem.id != null">
                        <el-input v-model="modifyRoleItem.id" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="名称">
                        <el-input v-model="modifyRoleItem.name"></el-input>
                    </el-form-item>
                    <el-form-item label="Code">
                        <el-input v-model="modifyRoleItem.code"></el-input>
                    </el-form-item>
                    <el-form-item label="Remark">
                        <el-input v-model="modifyRoleItem.remark"></el-input>
                    </el-form-item>
                    <el-form-item label="是否激活">
                        <el-switch v-model="modifyRoleItem.status"></el-switch>
                    </el-form-item>
                </el-form>
            </div>
            <div v-if="modalType == 2">
                <input type="hidden" v-model="modifyRoleId"/>
                <el-form ref="form" label-width="80px">
                    <el-checkbox-group v-model="menuIdChecked">
                        <ul>
                            <li v-for="roleMenuItem in roleMenuList">
                                <el-checkbox
                                    :value="roleMenuItem.menuId"
                                    :label="roleMenuItem.menuId"
                                    :key="roleMenuItem.menuId"
                                    name="roleId"
                                >{{ roleMenuItem.menuName }}
                                </el-checkbox>
                            </li>
                        </ul>

                    </el-checkbox-group>
                    <br/>
                </el-form>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" v-on:click="modalHide">取 消</el-button>
                <el-button type="primary" v-if="modalType == 1" v-on:click="saveRole(modifyRoleItem)">保存用户</el-button>
                <el-button type="info" v-if="modalType == 2" v-on:click="saveRoleMenu(modifyRoleItem.id)">保存用户权限
                </el-button>
            </div>
        </el-dialog>
        <!--  // dialog end  -->

    </div>
</template>

<script>
export default {
    name: "page-role",
    data: function () {
        return {
            loading: true,
            currentPage: 1, //page
            pageSize: 5, //limit
            pageSizes: [5, 15, 30, 50],
            total: 100,
            roleList: [],
            roleMenuList: [],
            modifyRoleItem: {},
            modalType: 1, // 1:新增权限/修改权限, 2: 编辑菜单
            menuIdChecked: [],
            modifyRoleId: '',
            isModalVisible: false,
            buzaModalTitle: 'Modal',
        }
    },
    mounted: function () {
        let _this = this;
        _this.listTable();
    },
    methods: {
        handleSizeChange(limit) {
            this.currentPage = 1;
            this.pageSize = limit;
            this.listTable();
        },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.listTable();
        },
        listTable() {
            let _this = this;
            _this.$request.get("/system/config/role/list?page=" + _this.currentPage + "&limit=" + _this.pageSize).then((response) => {
                _this.roleList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        addNewRoleHandler() {
            let _this = this;
            _this.modalType = 1;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增权限";
            _this.modifyRoleItem = {};
        },
        modalShow(roleItem, flag) {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = (flag === 1 ? "修改用户" : "编辑权限");
            roleItem.status = roleItem.status == "1" ? true : false;
            _this.modifyRoleItem = roleItem;
        },
        modalHide() {
            let _this = this;
            _this.isModalVisible = false;
            _this.modifyRoleItem = {};
        },
        modifyRoleHandler(roleItem) {
            let _this = this;
            _this.modalType = 1;
            _this.modalShow(roleItem, _this.modalType);
        },
        getRoleMenuHandler(roleItem) {
            let _this = this;
            _this.modalType = 2;
            let roleId = roleItem.id;
            _this.modifyRoleId = roleItem.id;
            _this.$request.get("/system/config/rolemenu/detail?roleId=" + roleId).then(response => {
                let menuTreeData = response.data.data;
                let cloneData = [];
                for (var i = 0; i < menuTreeData.length; i++) {
                    if (menuTreeData[i].menuParentId == 0) {
                        cloneData.push(menuTreeData[i]);
                    }
                    for (var k = 0; k < menuTreeData.length; k++) {
                        if (menuTreeData[i].menuId == menuTreeData[k].menuParentId) {
                            cloneData.push(menuTreeData[k]);
                        }
                    }
                }

                if (response.data.status == 200) {
                    _this.roleMenuList = cloneData;
                    let newArray = [];
                    _this.menuIdChecked = cloneData.filter(data => {
                        if (data.isContain == '1') {
                            newArray.push(data.menuId);
                            return data.menuId;
                        }
                    });
                    _this.menuIdChecked = newArray;
                    _this.isModalVisible = true;
                }
            });
        },
        saveRole(sysRoleDto) {
            let _this = this;
            _this.modalType = 1;
            sysRoleDto.status = sysRoleDto.status === true ? "1" : "0";
            _this.$request.post("/system/config/role/modify", sysRoleDto).then(response => {
                if (response.data.status == 200) {
                    _this.modalHide();
                    _this.listTable();
                } else {
                    alert(response.data.msg);
                }
            });

        },
        saveRoleMenu(roleId) {
            let _this = this;
            _this.$confirm('是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                _this.$request.post("/system/config/rolemenu/modify?roleId=" + _this.modifyRoleId + "&menuIds=" + _this.menuIdChecked.toString(),)
                    .then(response => {
                        if (response.data.code == 0) {
                            _this.$message.success(response.data.msg);
                        } else {
                            _this.$message.error(response.data.msg);
                        }
                    }).catch(response => {
                        _this.$message.error(response.data.msg);
                    });
                _this.isModalVisible = false;
            }).catch(response => {
                _this.isModalVisible = false;
                _this.$message.error("取消操作");
            });
        },
    }
}
</script>

<style scoped>

</style>