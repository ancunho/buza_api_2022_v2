<template>
  <div>
    <div class="page-header">
      <h1>Customer<small><i class="ace-icon fa fa-angle-double-right"></i>本店客户操作页面</small></h1>
    </div><!-- /.page-header -->

    <div class="row">
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
          <div class="col-xs-12">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-sm" v-on:click="addNewMenuHandler()" data-toggle="modal" data-target="#myModal">
              新增用户
            </button>
            <br/><br/>
            <table id="simple-table" class="table  table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">No.</th>
                  <th>Name.</th>
                  <th>Path.</th>
                  <th>Perms.</th>
                  <th class="center">Status</th>
                  <th class="center">Createtime</th>
                  <th class="center">Updatetime</th>
                  <th class="center">
                    caozuo
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="menuItem in menuList">
                  <td class="center">{{ menuItem.rn }}</td>
                  <td>{{ menuItem.name }}</td>
                  <td>{{ menuItem.path }}</td>
                  <td>{{ menuItem.perms }}</td>
                  <td class="center">
                    <span class="badge badge-danger" v-if="menuItem.status == '0'">{{ menuItem.statusName }}</span>
                    <span class="badge badge-success" v-if="menuItem.status == '1'">{{ menuItem.statusName }}</span>
                  </td>
                  <td class="center">{{ menuItem.createtime }}</td>
                  <td class="center">{{ menuItem.updatetime }}</td>
                  <td class="center">
                    <button class="btn btn-xs btn-info" @click="modifyMenuHandler(menuItem)"><i class="ace-icon fa fa-pencil bigger-120"> 修改</i></button>
                    &nbsp;&nbsp;
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
            <div class="form-group">
              <input type="hidden" v-model="modifyMenuItem.id" />
              <p><span>id</span> <input type="text" disabled v-model="modifyMenuItem.id" /> </p>
              <p><span>名称</span> <input type="text" v-model="modifyMenuItem.name" /> </p>
              <p><span>perms</span> <input type="text" v-model="modifyMenuItem.perms" /> </p>
              <p><span>type</span> <input type="text" v-model="modifyMenuItem.type" /> </p>
              <p><span>status</span>  <input type="text" v-model="modifyMenuItem.status" /> </p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="saveMenu(modifyMenuItem)">Save changes</button>
          </div>
        </div>
      </div>
    </div>




  </div>
</template>

<script>

export default {
  name: "page-menu",
  data: function() {
    return {
      menuList: [],
      modifyMenuItem: {}
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
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/config/menu/list").then((response) => {
        _this.menuList = response.data.data;
      })
    },
    addNewMenuHandler() {
      let _this = this;
      _this.modifyMenuItem = {};
    },
    modifyMenuHandler(menuItem) {
      let _this = this;
      $("#myModal").modal('show');
      _this.modifyMenuItem = menuItem;
    },
    saveMenu(sysMenuDto) {
      let _this = this;
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/config/menu/modify", sysMenuDto).then(response => {
        if (response.data.status == 200) {
          _this.list();
        } else {
          alert(response.data.msg);
        }
      });

    }
  }
}
</script>

<style scoped>

</style>