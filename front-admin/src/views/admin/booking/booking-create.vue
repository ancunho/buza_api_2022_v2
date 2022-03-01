<template>
    <div v-loading="loading">
        <el-form ref="form" :model="form" label-width="220px">
            <input type="hidden" v-model="form.postId" />
            <input type="hidden" v-model="form.bookingId" />
            <el-form-item label="预约人姓名">
                <el-input disabled v-model="form.bookingPersonName"></el-input>
            </el-form-item>
            <el-form-item label="预约人手机号">
                <el-input v-model="form.bookingMobile"></el-input>
            </el-form-item>
            <el-form-item label="门店">
                <el-select placeholder="请选择门店" v-model="form.shopId" @change="handleSelectShopItem(form.shopId)">
                    <el-option v-for="item in lstShopList" :label="item.shopName" :key="item.shopId" :value="item.shopId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Room">
                <el-select v-model="form.roomId" placeholder="请选择Room">
                    <el-option v-for="item in lstRoomList" :label="item.roomName" :key="item.roomId" :value="item.roomId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="预约人数">
                <el-input-number v-model="form.bookingPersonCount" :min="1" label="请输入预约人数"></el-input-number>
            </el-form-item>
            <el-form-item label="预约时间">
                <el-date-picker
                    style="width: 150px"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    v-model="form.bookingStartday"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>&nbsp;
                <el-time-select placeholder="起始时间" style="width:150px" v-model="form.bookingStarttime" :picker-options="{ start: '08:30', step: '00:15', end: '22:30'}">
                </el-time-select>&nbsp;
                <el-time-select placeholder="结束时间" style="width:150px" v-model="form.bookingEndtime" :picker-options="{ start: '08:30', step: '00:15', end: '22:30', minTime: form.bookingStarttime }">
                </el-time-select>
            </el-form-item>
            <el-form-item label="支付状态">
                <el-radio v-model="form.payStatus" label="01" border size="medium">已支付</el-radio>
                <el-radio v-model="form.payStatus" label="02" border size="medium">未支付</el-radio>
            </el-form-item>
            <el-form-item label="Remark">
                <el-input v-model="form.remark" placeholder="Remark"></el-input>
            </el-form-item>
            <el-form-item label="Comment">
                <el-input v-model="form.comment" placeholder="Comment"></el-input>
            </el-form-item>
            <el-form-item label="是否预约">
                <el-radio-group v-model="form.bookingStatus">
                    <el-radio label="1">是</el-radio>
                    <el-radio label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleOnSubmit">立即创建</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
export default {
    name: "booking-create",
    //Tool.getLoginUser()
    data() {
        return {
            form: {
                bookingId: '',
                bookingMobile: '',
                bookingType: '02', //01:miniapp, 02:management system
                shopId: '',
                roomId: '',
                customerId: '',
                bookingPersonCount: '',
                bookingStartday: '',
                bookingEndday: '',
                bookingStarttime: '',
                bookingEndtime: '',
                bookingStatus: '1',
                bookingPersonName: Tool.getStorageParam("username"), //백앤드에서 등록할때에는 관리자이름으로
                payStatus: '01',
                remark: '',
                comment: '',
                option01: '',
                option02: '',
                option03: '',
                option04: '',
                option05: '',
            },
            lstPostType: [],
            lstShopList: [],
            lstRoomList: [],
            loading: true,
        }
    },
    mounted() {
        let _this = this;
        _this.form.bookingId = _this.$route.params.bookingId;
        if (Tool.isNotNull(_this.form.bookingId)) {
            _this.getBookingInfoByBookingId(_this.form.bookingId);
        }
        _this.getShopList();
        _this.loading = false;
    },
    methods: {
        handleSelectShopItem(shopId) {
            let _this = this;
            _this.lstRoomList = {};
            _this.form.roomId = '';
            let params = {};
            params.shopId = shopId;
            _this.getRoomList(params);
        },
        getShopList() {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + '/system/shop/list?page=1&limit=100')
            .then(response => {
                _this.lstShopList = response.data.data;
            })
            .catch(response => {
                alert("getShopList Fail");
            });
        },
        getRoomList(params) {
            let _this = this;
            _this.$request.post(process.env.VUE_APP_SERVER + '/system/room/list?page=1&limit=100', params)
                .then(response => {
                    _this.lstRoomList = response.data.data;
                })
                .catch(response => {
                    alert("getShopList Fail");
                });
        },
        getBookingInfoByBookingId(bookingId) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + '/system/booking/info?bookingId=' + bookingId)
            .then(response => {
                if (response.data.code === 0) {
                    alert("OK");
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.loading = false;
            })
            .catch(response => {
                _this.$message.error("Fail");
            });
        },
        handleOnSubmit() {
            let _this = this;
            console.log("handleOnSubmit:", _this.form);
            _this.form.bookingEndday = _this.form.bookingStartday;
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/booking/proc", _this.form)
                .then(response => {
                    if (response.data.code === 0) {
                        _this.$message.success(response.data.msg);
                        _this.$router.push({name: 'booking/list'});
                    } else {
                        _this.$message.error(response.data.msg);
                    }
                }).catch(response => {
                    _this.$message.error(response.data.msg);
                });
        },

    }
}
</script>

<style>
#post-content-div > div {
    z-index: 2000!important;
}
</style>