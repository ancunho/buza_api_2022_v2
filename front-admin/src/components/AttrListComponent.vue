<template>
    <div v-loading="loading">
        <div style="padding: 30px;">
            <!--  table list start  -->
            <el-table :data="itemList" style="width: 100%; margin-top: 1.5rem;">
                <el-table-column prop="rn" label="编号" width="80"></el-table-column>
<!--                <el-table-column prop="attrId" label="Id" width="150" ></el-table-column>-->
                <el-table-column prop="attrName" label="Name" width="150" ></el-table-column>
                <el-table-column prop="attrValue" label="Value" width="150" ></el-table-column>
<!--                <el-table-column prop="attrType" label="Type" width="150" ></el-table-column>-->
<!--                <el-table-column prop="attrImage" label="Attr Image" width="150" >-->
<!--                    <template slot-scope="scope">-->
<!--                        <el-image :src="scope.row.attrImage" style="width: 100px; height: 100px;" />-->
<!--                    </template>-->
<!--                </el-table-column>-->
                <el-table-column prop="status" label="状态" align="center" width="120">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.status === '1' ? 'success' : 'danger'"> {{ scope.row.status === '1' ? '使用中' : '禁用' }} </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
                <el-table-column prop="updateTime" label="更新时间" width="150"></el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button @click="handleEmitAttrItem(scope.row)" type="primary" icon="el-icon-edit-outline">选择</el-button>
                        <!--          <el-button @click="getRoleHandler(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
            <!--  // table list end  -->
        </div>

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
    name: "AttrListComponent",
    data() {
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

        }
    },
    mounted() {
        let _this = this;
        _this.tableList();
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
        tableList() {
            let _this = this;
            _this.$request.post("/system/attr/list/type/2?page=" + _this.currentPage + "&limit=" + _this.pageSize, {}).then((response) => {
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
        handleEmitAttrItem(item) {
            let _this = this;
            _this.$emit("childEmitAttrItem", item);

        },
    }
}
</script>

<style scoped>

</style>