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
            <button type="button" class="btn btn-primary btn-sm" v-on:click="addNewCustomerHandler()" data-toggle="modal" data-target="#myModal">
              新增用户
            </button>
            <br/><br/>
            <table id="simple-table" class="table  table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">No.</th>
                  <th>Username.</th>
                  <th>Mobile.</th>
                  <th>Realname.</th>
                  <th>Shop.</th>
                  <th class="center">Status</th>
                  <th class="center">Createtime</th>
                  <th class="center">Updatetime</th>
                  <th class="center">
                    caozuo
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="customerItem in customerList">
                  <td class="center">{{ customerItem.rn }}</td>
                  <td>{{ customerItem.username }}</td>
                  <td>{{ customerItem.mobileNo }}</td>
                  <td>{{ customerItem.realname }}</td>
                  <td>{{ customerItem.shopName }}</td>
                  <td class="center">
                    <span class="badge badge-danger" v-if="customerItem.status == '0'">{{ customerItem.statusName }}</span>
                    <span class="badge badge-success" v-if="customerItem.status == '1'">{{ customerItem.statusName }}</span>
                  </td>
                  <td class="center">{{ customerItem.createTime }}</td>
                  <td class="center">{{ customerItem.updateTime }}</td>
                  <td class="center">
                    <button class="btn btn-xs btn-info" @click="modifyCustomerHandler(customerItem)"><i class="ace-icon fa fa-pencil bigger-120"> 修改</i></button>
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
              <input type="hidden" v-model="modifyCustomerItem.customerId" />
              <p v-if="modifyCustomerItem.customerId != null"><span>id</span> <input type="text" disabled v-model="modifyCustomerItem.customerId" /> </p>
              <p><span>username:</span> <input type="text" v-model="modifyCustomerItem.username" /> </p>
              <p><span>password:</span> <input type="text" v-model="modifyCustomerItem.password" /> </p>
              <p><span>realname:</span> <input type="text" v-model="modifyCustomerItem.realname" /> </p>
              <p><span>mobileNo:</span>  <input type="text" v-model="modifyCustomerItem.mobileNo" /> </p>
              <p><span>country:</span>  <input type="text" v-model="modifyCustomerItem.country" /> </p>
              <p><span>city:</span>  <input type="text" v-model="modifyCustomerItem.city" /> </p>
              <p><span>shopIds:</span>  <input type="text" v-model="modifyCustomerItem.shopId" /> </p>
              <p><span>status:</span>  <input type="text" v-model="modifyCustomerItem.status" /> </p>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" v-on:click="saveCustomer(modifyCustomerItem)">Save changes</button>
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
      customerList: [],
      modifyCustomerItem: {},
      shopList: [],
    }
  },
  mounted: function() {
    let _this = this;
    _this.list();
    _this.getShopList();
    $.getScript("/ace/assets/js/bootbox.js");
  },
  methods: {
    list() {
      let _this = this;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/customer/list").then((response) => {
        _this.customerList = response.data.data;
      })
    },
    getShopList() {
      let _this = this;
      _this.$request.get(process.env.VUE_APP_SERVER + "/system/shop/list").then(response => {
        console.log(response);
      })
    },
    addNewCustomerHandler() {
      let _this = this;
      _this.modifyCustomerItem = {};
    },
    modifyCustomerHandler(customerItem) {
      let _this = this;
      $("#myModal").modal('show');
      _this.modifyCustomerItem = customerItem;
    },
    saveCustomer(customerDto) {
      let _this = this;
      _this.$request.post(process.env.VUE_APP_SERVER + "/system/customer/proc", customerDto).then(response => {
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