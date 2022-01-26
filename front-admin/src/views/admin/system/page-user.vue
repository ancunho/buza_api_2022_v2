<template>
  <div>
    <div class="page-header">
      <h1>Menu<small><i class="ace-icon fa fa-angle-double-right"></i>XXXXXXX</small></h1>
    </div><!-- /.page-header -->

    <div class="row">
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
          <div class="col-xs-12">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-sm" v-on:click="addNewUserHandler()" data-toggle="modal" data-target="#myModal">
              新增权限
            </button>
            <br/><br/>
            <table id="simple-table" class="table  table-bordered table-hover">
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
                    <button class="btn btn-xs btn-info" @click="modifyUserHandler(userItem)"><i class="ace-icon fa fa-pencil bigger-120"> 修改</i></button>
                    &nbsp;
                    <button class="btn btn-xs btn-success" v-on:click="getRoleHandler(userItem)"><i class="ace-icon fa fa-trash-o bigger-120"> 编辑菜单</i></button>
                    &nbsp;
                    <button class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"> 删除</i></button>

                  </td>
                </tr>
              </tbody>
            </table>
          </div><!-- /.span -->
        </div><!-- /.row -->

        <div class="hr hr-18 dotted hr-double"></div>

        <!-- PAGE CONTENT ENDS -->
      </div><!-- /.col -->
    </div><!-- /.row -->

    <div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
          </div>
          <div class="modal-body">
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
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-if="modalType == 1" v-on:click="saveUser(modifyUserItem)">Save Role</button>
            <button type="button" class="btn btn-primary" v-if="modalType == 2" v-on:click="saveUserRole(modifyUserItem.id)">Save RoleMenu</button>
          </div>
        </div>
      </div>
    </div>

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
      modifyUserId: ''
    }
  },
  mounted: function() {
    let _this = this;
    $("body").mLoading();
    _this.list();
    $.getScript("/ace/assets/js/bootbox.js");
  },
  methods: {
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
      _this.modifyUserItem = {};
    },
    modalShow(userItem) {
      let _this = this;
      $("#myModal").modal('show');
      _this.modifyUserItem = userItem;
    },
    modalHide() {
      let _this = this;
      $("#myModal").modal('hide');
      _this.modifyUserItem = {};
    },
    modifyUserHandler(userItem) {
      let _this = this;
      _this.modalType = 1;
      _this.modalShow(userItem);
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
      _this.modalShow(userItem);
      });
    },
    saveUser(sysUserDto) {
      let _this = this;
      _this.modalType = 1;
      console.log("saveUser:", sysUserDto);
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/user/modify", sysUserDto).then(response => {
        if (response.data.status == 200) {
          _this.modalHide();
          _this.list();
        } else {
          alert(response.data.msg);
        }
      });

    },
    saveUserRole(roleId) {
      let _this = this;
      console.log(_this);
      Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
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