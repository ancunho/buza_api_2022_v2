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
            <button type="button" class="btn btn-primary btn-sm" v-on:click="addNewRoleHandler()" data-toggle="modal" data-target="#myModal">
              新增权限
            </button>
            <br/><br/>
            <table id="simple-table" class="table  table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">No.</th>
                  <th>Name.</th>
                  <th>Code.</th>
                  <th>Remark.</th>
                  <th class="center">Status</th>
                  <th class="center">Createtime</th>
                  <th class="center">Updatetime</th>
                  <th class="center">
                    操作
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="roleItem in roleList">
                  <td class="center">{{ roleItem.rn }}</td>
                  <td>{{ roleItem.name }}</td>
                  <td>{{ roleItem.code }}</td>
                  <td>{{ roleItem.remark }}</td>
                  <td class="center">
                    <span class="badge badge-danger" v-if="roleItem.status == '0'">{{ roleItem.statusName }}</span>
                    <span class="badge badge-success" v-if="roleItem.status == '1'">{{ roleItem.statusName }}</span>
                  </td>
                  <td class="center">{{ roleItem.createtime }}</td>
                  <td class="center">{{ roleItem.updatetime }}</td>
                  <td class="center">
                    <button class="btn btn-xs btn-info" @click="modifyRoleHandler(roleItem)"><i class="ace-icon fa fa-pencil bigger-120"> 修改</i></button>
                    &nbsp;
                    <button class="btn btn-xs btn-success" v-on:click="getRoleMenuHandler(roleItem)"><i class="ace-icon fa fa-trash-o bigger-120"> 编辑菜单</i></button>
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
              <input type="hidden" v-model="modifyRoleItem.id" />
              <p><span>id</span> <input type="text" disabled v-model="modifyRoleItem.id" /> </p>
              <p><span>名称</span> <input type="text" v-model="modifyRoleItem.name" /> </p>
              <p><span>Code</span> <input type="text" v-model="modifyRoleItem.code" /> </p>
              <p><span>Remark</span> <input type="text" v-model="modifyRoleItem.remark" /> </p>
              <p><span>status</span>  <input type="text" v-model="modifyRoleItem.status" /> </p>
            </div>
            <div class="form-group" v-if="modalType == 2">
              <input type="hidden" v-model="modifyRoleId" />
              <p v-for="roleMenuItem in roleMenuList" class="treeList">
                <span>
                  <input type="checkbox" :value="roleMenuItem.menuId" class="checkbox01" v-model="menuIdChecked" />
                </span>
                <span> &nbsp; {{ roleMenuItem.menuId }}</span>
                <strong> &nbsp; {{ roleMenuItem.menuName }}</strong>

              </p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-if="modalType == 1" v-on:click="saveRole(modifyRoleItem)">Save Role</button>
            <button type="button" class="btn btn-primary" v-if="modalType == 2" v-on:click="saveRoleMenu(modifyRoleItem.id)">Save RoleMenu</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: "page-role",
  data: function() {
    return {
      roleList: [],
      roleMenuList: [],
      modifyRoleItem: {},
      modalType: 1, // 1:新增权限/修改权限, 2: 编辑菜单
      menuIdChecked: [],
      modifyRoleId: ''
    }
  },
  mounted: function() {
    let _this = this;
    _this.list();
    $.getScript("/ace/assets/js/bootbox.js");
  },
  methods: {
    list() {
      let _this = this;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/role/list").then((response) => {
        _this.roleList = response.data.data;
      })
    },
    addNewRoleHandler() {
      let _this = this;
      _this.modalType = 1;
      _this.modifyRoleItem = {};
    },
    modalShow(roleItem) {
      let _this = this;
      $("#myModal").modal('show');
      _this.modifyRoleItem = roleItem;
    },
    modalHide() {
      let _this = this;
      $("#myModal").modal('hide');
      _this.modifyRoleItem = {};
    },
    modifyRoleHandler(roleItem) {
      let _this = this;
      _this.modalType = 1;
      _this.modalShow(roleItem);
    },
    getRoleMenuHandler(roleItem) {
      let _this = this;
      _this.modalType = 2;
      let roleId = roleItem.id;
      _this.modifyRoleId = roleItem.id;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/rolemenu/detail?roleId=" + roleId).then(response => {
        console.log("getRoleMenuHandler:", response);

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
          $("#myModal").modal('show');
        }
      });
    },
    saveRole(sysRoleDto) {
      let _this = this;
      _this.modalType = 1;
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/role/modify", sysRoleDto).then(response => {
        if (response.data.status == 200) {
          _this.modalHide();
          _this.list();
        } else {
          alert(response.data.msg);
        }
      });

    },
    saveRoleMenu(roleId) {
      let _this = this;
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
          _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/rolemenu/modify?roleId=" + _this.modifyRoleId + "&menuIds=" + _this.menuIdChecked.toString(), )
          .then(reponse => {
            if (reponse.data.status == 200) {
              Swal.fire(
                  'roleId:' + _this.modifyRoleId,
                  'menuId:' + _this.menuIdChecked,
                  'success'
              )
            } else {
              alert("Fail");
            }
          })
          .catch(response => {
            console.log("role/modify->fail:", response);
            alert("Fail");
          });

        }
      })
      // console.log("saveRoleMenu:","roleId:",_this.modifyRoleId);
      // console.log("saveRoleMenu:", _this.menuIdChecked);
    },
  }
}
</script>

<style scoped>

</style>