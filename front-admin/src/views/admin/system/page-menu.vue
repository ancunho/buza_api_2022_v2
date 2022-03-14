<template>
    <div v-loading="loading">

        <el-button @click="addNewMenuHandler()" type="primary" icon="el-icon-plus">新增菜单</el-button>

        <!--  table list start  -->
        <el-table :data="menuList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="name" label="菜单名" width="150"></el-table-column>
            <el-table-column prop="path" label="路径" width="150"></el-table-column>
            <el-table-column prop="perms" label="权限code" width="250"></el-table-column>
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
                    <el-button @click="modifyMenuHandler(scope.row)" type="primary" icon="el-icon-edit-outline">修改菜单</el-button>
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
            <input type="hidden" v-model="modifyMenuItem.id"/>
            <el-form ref="form" label-width="80px">
                <el-form-item label="ID" v-if="modifyMenuItem.id != null">
                    <el-input v-model="modifyMenuItem.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="modifyMenuItem.name"></el-input>
                </el-form-item>
                <el-form-item label="Perms">
                    <el-input v-model="modifyMenuItem.perms"></el-input>
                </el-form-item>
                <el-form-item label="type">
                    <el-input v-model="modifyMenuItem.type"></el-input>
                </el-form-item>
                <el-form-item label="是否激活">
                    <el-switch v-model="modifyMenuItem.status"></el-switch>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="danger" v-on:click="isModalVisible = false">取 消</el-button>
                <el-button type="primary" v-on:click="saveMenu(modifyMenuItem)">保存用户</el-button>
            </div>
        </el-dialog>
        <!--  // dialog end  -->

    </div>
</template>

<script>

export default {
    name: "page-menu",
    data: function () {
        return {
            menuList: [],
            modifyMenuItem: {},
            loading: true,
            currentPage: 1, //page
            pageSize: 5, //limit
            pageSizes: [5, 15, 30, 50],
            total: 100,
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
            _this.$request.get("/system/config/menu/list?page=" + _this.currentPage + "&limit=" + _this.pageSize).then((response) => {
                _this.menuList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        addNewMenuHandler() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增菜单";
            _this.modifyMenuItem = {};
        },
        modifyMenuHandler(menuItem) {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "编辑菜单";
            menuItem.status = menuItem.status == "1" ? true : false;
            _this.modifyMenuItem = menuItem;
        },
        saveMenu(sysMenuDto) {
            let _this = this;
            sysMenuDto.status = sysMenuDto.status === true ? "1" : "0";
            _this.$request.post("/system/config/menu/modify", sysMenuDto).then(response => {
                if (response.data.status == 200) {
                    _this.listTable();
                } else {
                    alert(response.data.msg);
                }
                _this.isModalVisible = false;
            });

        }
    }
}
</script>

<style scoped>

</style>