<template>
  <div>
    <div class="page-header">
      <h1>Shop<small><i class="ace-icon fa fa-angle-double-right"></i>XXXXXXX</small></h1>
    </div><!-- /.page-header -->

    <div class="row">
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
          <div class="col-xs-12">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-sm" v-on:click="addNewHandler()" data-toggle="modal" data-target="#myModal">
              新增门店
            </button>
            <br/><br/>
            <table id="simple-table" class="table  table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">No.</th>
                  <th>shopName.</th>
                  <th>shopMobile.</th>
                  <th>managerName.</th>
                  <th>managerMobile.</th>
                  <th>shopIntro.</th>
                  <th class="center">Status</th>
                  <th class="center">Createtime</th>
                  <th class="center">Updatetime</th>
                  <th class="center">
                    caozuo
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in itemList">
                  <td class="center">{{ item.rn }}</td>
                  <td>{{ item.shopName }}</td>
                  <td>{{ item.shopMobile }}</td>
                  <td>{{ item.managerName }}</td>
                  <td>{{ item.managerMobile }}</td>
                  <td>{{ item.shopIntro }}</td>
                  <td class="center">
                    <span class="badge badge-danger" v-if="item.shopStatus == '0'">{{ item.statusName }}</span>
                    <span class="badge badge-success" v-if="item.shopStatus == '1'">{{ item.statusName }}</span>
                  </td>
                  <td class="center">{{ item.createTime }}</td>
                  <td class="center">{{ item.updateTime }}</td>
                  <td class="center">
                    <button class="btn btn-xs btn-info" @click="modifyItemHandler(item)"><i class="ace-icon fa fa-pencil bigger-120"> 修改</i></button>
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
              <input type="hidden" v-model="modifyItem.shopId" />
              <p v-if="modifyItem.shopId != null"><span>id</span> <input type="text" disabled v-model="modifyItem.shopId" /> </p>
              <p><span>shopName:</span> <input type="text" v-model="modifyItem.shopName" /> </p>
              <p><span>shopIntro:</span> <input type="text" v-model="modifyItem.shopIntro" /> </p>
              <p><span>managerName:</span> <input type="text" v-model="modifyItem.managerName" /> </p>
              <p><span>managerMobile:</span> <input type="text" v-model="modifyItem.managerMobile" /> </p>
              <p><span>shopMobile:</span>  <input type="text" v-model="modifyItem.shopMobile" /> </p>
              <p><span>shopStatus:</span>  <input type="text" v-model="modifyItem.shopStatus" /> </p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="saveItem(modifyItem)">Save changes</button>
          </div>
        </div>
      </div>
    </div>




  </div>
</template>

<script>

export default {
  name: "customer-list",
  data: function() {
    return {
      itemList: [],
      modifyItem: {}
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
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/shop/list").then((response) => {
        _this.itemList = response.data.data;
      })
    },
    addNewHandler() {
      let _this = this;
      _this.modifyItem = {};
    },
    modifyItemHandler(item) {
      let _this = this;
      $("#myModal").modal('show');
      _this.modifyItem = item;
    },
    saveItem(item) {
      let _this = this;
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/shop/proc", item).then(response => {
        if (response.data.code == 0) {
          $("#myModal").modal('hide');
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