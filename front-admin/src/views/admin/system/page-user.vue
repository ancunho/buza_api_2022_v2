<template>
  <div>
    <el-button @click="addNewUserHandler" icon="el-icon-plus">新增用户</el-button>
    <table id="simple-table" class="table01">
      <thead>
      <tr>
        <th class="center">No.</th>
        <th>Username.</th>
        <th>Realname.</th>
        <th class="center">Status</th>
        <th class="center">Createtime</th>
        <th class="center">Updatetime</th>
        <th class="center">
          操作
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in userList">
        <input type="hidden" :value="item.userSeq" />
        <td class="center">{{ item.rn }}</td>
        <td>{{ item.username }}</td>
        <td>{{ item.realname }}</td>
        <td class="center">
          <el-tag type="danger" v-if="item.status == '0'"> {{ item.statusName }} </el-tag>
          <el-tag type="success" v-if="item.status == '1'"> {{ item.statusName }} </el-tag>
        </td>
        <td class="center">{{ item.createtime }}</td>
        <td class="center">{{ item.updatetime }}</td>
        <td class="center">
          <el-button @click="modifyUserHandler(item)" type="primary" plain icon="el-icon-edit-outline">修改信息</el-button>
          <el-button @click="getRoleHandler(item)" type="info" plain icon="el-icon-edit-outline">编辑权限</el-button>
          <el-button @click="getRoleHandler(item)" type="danger" plain icon="el-icon-delete">删除</el-button>
        </td>
      </tr>
      </tbody>
    </table>

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

    }
  },

  mounted: function() {
    let _this = this;
    $("body").mLoading();
    _this.list();
    $.getScript("/ace/assets/js/bootbox.js");
  },

  methods: {
    buzaModalClose() {
      this.isModalVisible = false
    },
    list() {
      let _this = this;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/user/list").then((response) => {
        $("body").mLoading("hide");
        _this.userList = response.data.data;
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
          _this.$message.info(response.data.msg);
          _this.modalHide();
          _this.list();
        } else {
          _this.$message.error(response.data.msg);
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
                _this.$message.success(response.data.msg);
              } else {
                _this.$message.error(response.data.msg);
              }
            })
            .catch(response => {
              console.log("user/role/modify->fail:", response);
              _this.$message.error(response.data.msg);
            });
        _this.isModalVisible = false;
      }).catch(response => {
        _this.isModalVisible = false;
        _this.$message.error(response.data.msg);
      });

    },
  }
}
</script>

<style scoped>

</style>