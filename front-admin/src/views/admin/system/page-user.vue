<template>
  <div>
    <button class="btn btn-primary" @click="addNewUserHandler"><span class="las la-plus-circle"></span>新增用户</button>
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
      <tr v-for="userItem in userList">
        <input type="hidden" :value="userItem.userSeq" />
        <td class="center">{{ userItem.rn }}</td>
        <td>{{ userItem.username }}</td>
        <td>{{ userItem.realname }}</td>
        <td class="center">
          <span class="badge badge-danger" v-if="userItem.status == '0'">{{ userItem.statusName }}</span>
          <span class="badge badge-success" v-if="userItem.status == '1'">{{ userItem.statusName }}</span>
        </td>
        <td class="center">{{ userItem.createtime }}</td>
        <td class="center">{{ userItem.updatetime }}</td>
        <td class="center">
          <button class="btn01" @click="modifyUserHandler(userItem)"><span class="las la-edit"> 修改</span></button>
          &nbsp;
          <button class="btn01" v-on:click="getRoleHandler(userItem)"><span class="las la-sitemap"> 编辑菜单</span></button>
          &nbsp;
          <button class="btn01"><span class="las la-times"> 删除</span></button>
        </td>
      </tr>
      </tbody>
    </table>

    <BuzaModal
        v-bind:title="buzaModalTitle"
        v-show="isModalVisible"
        v-on:buzaModalClose="buzaModalClose"
    >
      <div slot="modal-body">
        <div class="form-group" v-if="modalType == 1">
          <input type="hidden" v-model="modifyUserItem.userSeq" />
          <p><span>id</span> <input type="text" disabled v-model="modifyUserItem.userSeq" /> </p>
          <p><span>username</span> <input type="text" v-model="modifyUserItem.username" /> </p>
          <p v-if="modifyUserItem.userSeq == null"><span>PASSWORD</span> <input type="text" v-model="modifyUserItem.password" /> </p>
          <p><span>real_name</span> <input type="text" v-model="modifyUserItem.realname" /> </p>
          <p><span>status</span>  <input type="text" v-model="modifyUserItem.status" /> </p>
        </div>
        <div class="form-group" v-if="modalType == 2">
          <input type="hidden" v-model="modifyUserId" />
          <p v-for="roleItem in roleList" class="treeList">
                <span>
                  <input type="checkbox" :value="roleItem.roleId" class="checkbox01" v-model="roleIdChecked" />
                </span>
            <span> &nbsp; {{ roleItem.roleId }}</span>
            <strong> &nbsp; {{ roleItem.roleName }}</strong>
            <strong> &nbsp; {{ roleItem.roleRemark }}</strong>
          </p>
        </div>
      </div>

      <div slot="modal-footer">
        <div style="display: flex; justify-content: center">
          <button type="button" class="btn01 btn-white" v-on:click="modalHide">Close</button>
          <button type="button" class="btn01 btn-primary" v-if="modalType == 1" v-on:click="saveUser(modifyUserItem)">Save User</button>
          <button type="button" class="btn01 btn-primary" v-if="modalType == 2" v-on:click="saveUserRole(modifyUserItem.id)">Save User Role</button>
        </div>
      </div>
    </BuzaModal>

  </div>
</template>

<script>

import BuzaModal from "@/components/BuzaModal";

export default {
  name: "page-user",

  components: {
    BuzaModal
  },

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
        console.log("userList:", _this.userList);
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

      let cloneData = [];

      for (var i = 0; i < _this.roleList.length; i++) {
        if (_this.roleList[i].isContain == '1') {
          cloneData.push(_this.roleList[i].roleId);
        }
      }
      _this.roleIdChecked = cloneData;
      console.log("resultData:", _this.roleList);
      _this.modalShow(userItem, 2);
      });
    },
    saveUser(sysUserDto) {
      let _this = this;
      _this.modalType = 1;
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/user/modify", sysUserDto).then(response => {
        console.log(response);
        if (response.data.code == 0) {
          _this.modalHide();
          _this.list();
        } else {
          alert(response.data.msg);
        }
      });

    },
    saveUserRole(roleId) {
      let _this = this;
      _this.isModalVisible = false;
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Do it!'
      }).then((result) => {
        if (result.isConfirmed) {
          _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/user/role/modify?userSeq=" + _this.modifyUserId + "&roleIds=" + _this.roleIdChecked.toString(), )
          .then(reponse => {
            if (reponse.data.status == 200) {
              Swal.fire(
                  'userSeq:' + _this.modifyUserId,
                  'roleIds:' + _this.roleIdChecked,
                  'success'
              )
            } else {
              alert("Fail");
            }
          })
          .catch(response => {
            console.log("user/role/modify->fail:", response);
            alert("Fail");
          });

        }
      }).catch(result => {
        alert(reslut);
      })
    },
  }
}
</script>

<style scoped>

</style>