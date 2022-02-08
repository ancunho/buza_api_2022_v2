<template>
  <div v-loading="loading">
    <el-button @click="addNewUserHandler" type="primary" icon="el-icon-plus">新增用户</el-button>
    <el-table :data="userList" style="width: 100%; margin-top: 1.5rem;">
      <el-table-column prop="userSeq" label="userSeq" v-if="false"></el-table-column>
      <el-table-column prop="rn" label="编号" width="120"></el-table-column>
        <el-table-column prop="username" label="账号" width="200"></el-table-column>
      <el-table-column prop="realname" label="真是姓名" width="220"></el-table-column>
      <el-table-column prop="status" align="center" label="状态" width="120">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.status == '0'"> {{ scope.row.statusName }} </el-tag>
          <el-tag type="success" v-if="scope.row.status == '1'"> {{ scope.row.statusName }} </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间" width="220"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间" width="220"></el-table-column>
      <el-table-column label="操作" align="center" >
        <template slot-scope="scope">
          <el-button @click="modifyUserHandler(scope.row)" type="primary" icon="el-icon-edit-outline">修改信息</el-button>
          <el-button @click="getRoleHandler(scope.row)" type="info" icon="el-icon-lock">编辑权限</el-button>
<!--          <el-button @click="getRoleHandler(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <el-row style="margin: 2rem 0;">
      <el-col :span="12" :offset="6">
        <el-pagination
            @current-change="handlePage"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 50]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </el-col>
    </el-row>

    <el-dialog v-bind:title="buzaModalTitle" :visible.sync="isModalVisible"> <!--:close-on-click-modal="false"-->
      <div v-if="modalType == 1">
        <input type="hidden" v-model="modifyUserItem.userSeq" />
        <el-form ref="form" label-width="80px">
          <el-form-item label="ID" v-if="modifyUserItem.userSeq != null">
            <el-input v-model="modifyUserItem.userSeq" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="modifyUserItem.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" v-if="modifyUserItem.userSeq == null">
            <el-input v-model="modifyUserItem.password"></el-input>
          </el-form-item>
          <el-form-item label="真实姓名">
            <el-input v-model="modifyUserItem.realname"></el-input>
          </el-form-item>
          <el-form-item label="是否激活">
            <el-switch v-model="modifyUserItem.status"></el-switch>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="modalType == 2">
        <input type="hidden" v-model="modifyUserId" />
        <el-form ref="form" label-width="80px">
          <el-checkbox-group v-model="roleIdChecked">
            <ul>
              <li v-for="roleItem in roleList">
                <el-checkbox
                    :value="roleItem.roleId"
                    :label="roleItem.roleId"
                    :key="roleItem.roleId"
                    name="roleId"
                >{{roleItem.roleName}}</el-checkbox>
              </li>
            </ul>

          </el-checkbox-group>
          <br/>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" v-on:click="modalHide">取 消</el-button>
        <el-button type="primary" v-if="modalType == 1" v-on:click="saveUser(modifyUserItem)">保存用户</el-button>
        <el-button type="info" v-if="modalType == 2" v-on:click="saveUserRole(modifyUserItem.id)">保存用户权限</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "page-user",

  data: function() {
    return {
      userList: [],
      roleList: [],
      modifyUserItem: {},
      modalType: 1, // 1:新增权限/修改权限, 2: 编辑菜单
      roleIdChecked: [],
      modifyUserId: '',
      isModalVisible: false,
      buzaModalTitle: 'Modal',
      loading: true,
      currentPage: 1,
      pageSize: 2, //limit
      total: 1,
    }
  },

  mounted: function() {
    let _this = this;
    _this.listTable(_this.currentPage, _this.pageSize);
  },

  methods: {
    handlePage() {
      this.listTable(this.currentPage, this.pageSize);
    },
    listTable(page,limit) {
      let _this = this;
      console.log("page:", page, ", limit:", limit);
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/user/list?page=" + page + "&limit=" + limit).then((response) => {
        _this.userList = response.data.data;
        _this.currentPage = page;
        _this.pageSize = limit;
        _this.total = response.data.count;
        _this.loading = false;
        console.log("_this.userList", _this.userList);
      })
    },
    addNewUserHandler() {
      let _this = this;
      _this.modalType = 1;
      _this.isModalVisible = true;
      _this.buzaModalTitle = "新增用户";
      _this.modifyUserItem = {};
    },
    modalShow(userItem, flag) {
      let _this = this;
      _this.isModalVisible = true;
      _this.buzaModalTitle = (flag === 1 ? "修改用户" : "编辑权限");
      userItem.status = userItem.status == "1" ? true : false;
      _this.modifyUserItem = userItem;
    },
    modalHide() {
      let _this = this;
      _this.isModalVisible = false;
      _this.modifyUserItem = {};
    },
    modifyUserHandler(userItem) {
      let _this = this;
      _this.modalType = 1;
      _this.modalShow(userItem, 1);
    },
    getRoleHandler(userItem) {
      let _this = this;
      _this.modalType = 2;

      let userSeq = userItem.userSeq;
      _this.modifyUserId = userItem.userSeq;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/user/role?userSeq=" + userSeq).then(response => {
      _this.roleList = response.data.data;
      console.log("_this.roleList", _this.roleList);

      let cloneData = [];

      for (var i = 0; i < _this.roleList.length; i++) {
        if (_this.roleList[i].isContain == '1') {
          cloneData.push(_this.roleList[i].roleId);
        }
      }

      _this.roleIdChecked = cloneData;
      _this.modalShow(userItem, 2);
      });
    },
    saveUser(sysUserDto) {
      let _this = this;
      _this.modalType = 1;
      sysUserDto.status = sysUserDto.status === true ? "1" : "0";
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/user/modify", sysUserDto).then(response => {
        if (response.data.code == 0) {
          _this.$notify.success(response.data.msg);
          _this.modalHide();
          _this.list();
        } else {
          _this.$notify.error(response.data.msg);
        }
      });

    },
    saveUserRole(roleId) {
      let _this = this;

      console.log("_this.roleIdChecked", _this.roleIdChecked);
      _this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        _this.$request
            .post(process.env.VUE_APP_SERVER + "/system/config/user/role/modify?userSeq=" + _this.modifyUserId + "&roleIds=" + _this.roleIdChecked.toString(), )
            .then(response => {
              if (response.data.code == 0) {
                _this.$notify.success(response.data.msg);
              } else {
                _this.$notify.error(response.data.msg);
              }
            })
            .catch(response => {
              console.log("user/role/modify->fail:", response);
              _this.$notify.error(response.data.msg);
            });
        _this.isModalVisible = false;
      }).catch(response => {
        _this.isModalVisible = false;
        _this.$notify.error(response.data.msg);
      });

    },
  }
}
</script>

<style scoped>

</style>