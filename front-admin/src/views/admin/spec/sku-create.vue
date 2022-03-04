<template>
    <div v-loading="loading">
        <el-form ref="form" :model="form" label-width="120px">
            <input type="hidden" v-model="form.skuId" />
            <el-form-item label="SKU名称">
                <el-input v-model="form.skuName"></el-input>
            </el-form-item>
            <el-form-item label="skuIntro">
                <el-input v-model="form.skuIntro"></el-input>
            </el-form-item>
            <el-form-item label="skuPrice">
                <el-input type="number" v-model="form.skuPrice"></el-input>
            </el-form-item>
            <el-form-item label="skuCode">
                <el-input v-model="form.skuCode"></el-input>
            </el-form-item>
            <el-form-item label="skuBarcode">
                <el-input v-model="form.skuBarcode"></el-input>
            </el-form-item>
            <el-form-item label="skuStock">
                <el-input type="number" v-model="form.skuStock"></el-input>
            </el-form-item>
            <el-form-item label="skuUnit">
                <el-input v-model="form.skuUnit"></el-input>
            </el-form-item>
            <el-form-item label="skuUnitName">
                <el-input v-model="form.skuUnitName"></el-input>
            </el-form-item>
            <el-form-item label="price">
                <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="vipPrice">
                <el-input v-model="form.vipPrice"></el-input>
            </el-form-item>
            <el-form-item label="是否发布">
                <el-radio-group v-model="form.status">
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
    name: "sku-create",

    data() {
        return {
            form: {
                skuId: '',
                spuId: '',
                skuName: '',
                skuIntro: '',
                skuPrice: 0,
                skuCode: '',
                skuBarcode: '',
                skuStock: '',
                skuUnit: '',
                skuUnitName: '',
                price: 0,
                vipPrice: 0,
                status: '1',
                mainImage01: '',
                mainImage02: '',
                mainImage03: '',
                subImage01: '',
                subImage02: '',
                subImage03: '',
                subImage04: '',
                subImage05: '',
                option01: '',
                option02: '',
                option03: '',
                option04: '',
                option05: '',
            },
            lstSpu: [],
            loading: true,
        }
    },
    mounted() {
        let _this = this;

        _this.setListCommonCode("POST_TYPE");
        _this.form.postId = _this.$route.params.postId;
        if (Tool.isNotNull(_this.form.postId)) {
            _this.getPostInfoByPostId(_this.form.postId);
        }
        _this.loading = false;
    },
    methods: {
        setListCommonCode(codeType) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + "/system/code/type/list?codeType=" + codeType).then((response) => {
                _this.lstPostType = response.data.data;
            })
        },
        getPostInfoByPostId(postId) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + '/system/post/info?postId=' + postId)
            .then(response => {
                if (response.data.code === 0) {
                    _this.form = response.data.data;
                    _this.form.isJoin = _this.form.isJoin === "1";
                    _this.form.isNeedPay = _this.form.isNeedPay === "1";
                    _this.form.eventDateRange = [_this.form.eventStartTime || '', _this.form.eventEndTime || ''];
                    _this.editor.txt.html(_this.form.postContent);
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.loading = false;
            })
            .catch(response => {
                alert("Fail");
            });
        },
        handleOnSubmit() {
            let _this = this;
            _this.form.eventStartTime = _this.form.eventDateRange[0];
            _this.form.eventEndTime = _this.form.eventDateRange[1];
            _this.form.postContent = _this.editorData;
            _this.form.isJoin = _this.form.isJoin == true ? "1" : "0";
            _this.form.isNeedPay = _this.form.isNeedPay == true ? "1" : "0";
            _this.$request.post(process.env.VUE_APP_SERVER + "/system/post/proc", _this.form)
                .then(response => {
                    if (response.data.code === 0) {
                        _this.$message.success(response.data.msg);
                        _this.$router.push({name: 'post/list'});
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