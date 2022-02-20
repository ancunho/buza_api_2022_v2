<template>
    <div v-loading="loading">

        <el-button @click="handleAddNew()" type="primary" icon="el-icon-plus">新增文章</el-button>
<!--        <router-link to="/post/create"><el-button type="primary" icon="el-icon-plus">新增Room</el-button></router-link>-->

        <!--  table list start  -->
        <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
            <el-table-column prop="rn" label="编号" width="80"></el-table-column>
            <el-table-column prop="roomName" label="roomName" width="150" ></el-table-column>
            <el-table-column prop="roomIntro" label="roomIntro" ></el-table-column>
            <el-table-column prop="roomCapacity" label="roomCapacity" ></el-table-column>
            <el-table-column prop="roomStatus" label="状态" align="center" width="120">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.roomStatus == '0'"> {{ scope.row.statusName }}</el-tag>
                    <el-tag type="success" v-if="scope.row.roomStatus == '1'"> {{ scope.row.statusName }}</el-tag>
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
            <input type="hidden" v-model="modifyItem.roomId"/>
            <el-form ref="form" label-width="130px">
                <el-form-item label="ID" v-if="modifyItem.roomId != null">
                    <el-input v-model="modifyItem.roomId" disabled></el-input>
                </el-form-item>
                <el-form-item label="门店">
                    <el-select v-model="shopId" placeholder="请选择门店">
                        <el-option v-for="item in shopList" :label="item.shopName" :key="item.shopId" :value="item.shopId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="Room名">
                    <el-input v-model="modifyItem.roomName"></el-input>
                </el-form-item>
                <el-form-item label="Room介绍">
                    <el-input v-model="modifyItem.roomIntro"></el-input>
                </el-form-item>
                <el-form-item label="Room Capacity">
                    <el-input v-model="modifyItem.roomCapacity"></el-input>
                </el-form-item>
                <el-form-item label="是否激活">
                    <el-switch v-model="modifyItem.roomStatus"></el-switch>
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
            shopList: [],
            shopId: '',
        }
    },
    mounted: function () {
        let _this = this;
        _this.tableList();
        _this.getShopList();
    },
    methods: {
        handleSizeChange(limit) {
            this.currentPage = 1;
            this.pageSize = limit;
            this.tableList();
        },
        handleCurrentChange(page) {
            this.currentPage = page;
            this.tableList();
        },
        getShopList() {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + "/system/shop/list?page=1&limit=10")
            .then(response => {
                console.log("shop_list:", response);
                if (response.data.code === 0) {
                    _this.shopList = response.data.data;
                }
            })
            .catch(response => {
                console.log("shop_list catch:", response);
            });
        },
        tableList() {
            let _this = this;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/room/list?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
                _this.itemList = response.data.data;
                _this.total = response.data.count;
                _this.loading = false;
            })
        },
        handleAddNew() {
            let _this = this;
            _this.isModalVisible = true;
            _this.buzaModalTitle = "新增Room";
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
            item.roomStatus = item.roomStatus === true ? "1" : "0";
            item.shopId = _this.shopId;
            console.log("save Room Item: ", item);
            _this.loading = true;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/room/proc", item).then(response => {
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