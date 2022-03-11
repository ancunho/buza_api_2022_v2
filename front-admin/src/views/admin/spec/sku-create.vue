<template>
    <div v-loading="loading">
        <el-form ref="form" :model="form" label-width="120px">
            <input type="hidden" v-model="form.skuId" />
            <el-form-item label="SPU">
                <input type="hidden" v-model="form.spuId" />
                {{ form.spuName }}
                <el-button type="info" v-on:click="handleChooseSpu('selectSpu')">选 择 S P U</el-button>
            </el-form-item>
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="skuName">
                        <el-input v-model="form.skuName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="skuPrice">
                        <el-input type="number" v-model="form.skuPrice" :min="0"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>


            <el-form-item label="skuIntro">
                <el-input v-model="form.skuIntro"></el-input>
            </el-form-item>

            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="skuCode">
                        <el-input v-model="form.skuCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="skuBarcode">
                        <el-input v-model="form.skuBarcode"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row :gutter="20">
                <el-col :span="8">
                    <el-form-item label="skuStock">
                        <el-input v-model="form.skuStock" type="number" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="skuUnit">
                        <el-input v-model="form.skuUnit"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="skuUnitName">
                        <el-input v-model="form.skuUnitName"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="price">
                        <el-input v-model="form.price" type="number" :min="0"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="vipPrice">
                        <el-input v-model="form.vipPrice" type="number" :min="0"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row :gutter="20">
                <el-col :span="12">
                    <el-form-item label="规格">
                        <el-button type="info" v-on:click="isDrawerForAttrVisible = true">新增规格</el-button>
                        <el-table :data="form.lstSkuAttr" style="width: 90%">
                            <el-table-column prop="attrId" label="Attr" width="120" >
                                <template slot-scope="scope">
                                    {{ scope.row.attrName }}
                                </template>
                            </el-table-column>
                            <el-table-column prop="attrValue" label="Value" width="120" >
                                <template slot-scope="scope">
                                    {{ scope.row.attrValue }}
                                </template>
                            </el-table-column>
                            <el-table-column prop="pricePlus" label="plus prie" width="120" >
                                <template slot-scope="scope">
                                    <el-input v-model="scope.row.pricePlus" :width="50" type="number"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作">
                                <template slot-scope="scope">
                                    <el-button type="danger" v-on:click="handleRemoveAttrValue(scope.row)" size="small" icon="el-icon-delete" circle></el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="主图片">
                        <div class="block">
                            <span class="demonstration"></span>
                            <el-image :src="form.mainImage01 || ''" style="width:100px; height:100px;">
                                <div slot="error" class="image-slot">
                                    <i class="el-icon-picture-outline font100px" style="color:#ddd;"></i>
                                </div>
                            </el-image>
                        </div>
                        <el-button type="info" v-on:click="handleChooseMain('mainImage01')">选 择 图 片</el-button>
                        <el-button type="danger" v-on:click="handleDeleteChooseMain('mainImage01')">删 除</el-button>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-form-item label="是否发布">
                <el-radio-group v-model="form.status">
                    <el-radio label="1">是</el-radio>
                    <el-radio label="0">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleOnSubmit">保 存</el-button>
                <el-button>取 消</el-button>
            </el-form-item>
        </el-form>

        <!--  // drawer start  -->
        <el-drawer
            v-loading="loadingDrawer"
            title="图片详细信息"
            :visible.sync="isDrawerVisible"
            direction="rtl"
            size="80%"
            :close-on-press-escape="false"
        >
            <FileListComponent @childEmitItem="childEmitItem" />
        </el-drawer>
        <!--  // drawer end  -->

        <!--  // spu drawer start  -->
        <el-drawer
            v-loading="loadingDrawer"
            title="请选择SPU"
            :visible.sync="isDrawerForSpuVisible"
            direction="rtl"
            size="60%"
            :close-on-press-escape="false"
        >
            <div style="padding: 30px;">
                <SpuListComponent @childEmitSpuItem="childEmitSpuItem" />
            </div>
        </el-drawer>
        <!--  // spu drawer end  -->

        <!--  // spu drawer start  -->
        <el-drawer
            v-loading="loadingDrawer"
            title="请选择SPU"
            :visible.sync="isDrawerForAttrVisible"
            direction="rtl"
            size="60%"
            :close-on-press-escape="false"
        >
            <div style="padding: 30px;">
                <AttrListComponent @childEmitAttrItem="childEmitAttrItem" />
            </div>
        </el-drawer>
        <!--  // spu drawer end  -->


    </div>

</template>

<script>
import FileListComponent from '@/components/FileListComponent';
import SpuListComponent from '@/components/SpuListComponent';
import AttrListComponent from '@/components/AttrListComponent';
export default {
    name: "sku-create",
    components: {
        AttrListComponent,
        SpuListComponent,
        FileListComponent
    },
    data() {
        return {
            form: {
                skuId: '',
                spuId: '',
                spuName: '',
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
                lstSkuAttr: [],
            },
            lstSpu: [],
            loading: true,
            isDrawerVisible: false,
            loadingDrawer: false,
            choosenFlag: '',

            isDrawerForSpuVisible: false,
            isDrawerForAttrVisible: false,
        }
    },
    mounted() {
        let _this = this;
        _this.setListCommonCode("POST_TYPE");
        _this.form.skuId = _this.$route.params.skuId;
        if (Tool.isNotNull(_this.form.skuId)) {
            _this.getSkuInfoBySkuId(_this.form.skuId);
        }
        _this.loading = false;
    },
    methods: {
        handleRemoveAttrValue(item) {
            let _this = this;
            _this.form.lstSkuAttr.splice(_this.form.lstSkuAttr.indexOf(item),1);
        },
        childEmitAttrItem(data) {
            let _this = this;
            data.skuId = _this.form.skuId;
            console.log(data);
            _this.isDrawerForAttrVisible = false;
            _this.form.lstSkuAttr.push(data);
            _this.form.lstSkuAttr = _this.form.lstSkuAttr.filter((item, idx, self) => {
                return self.indexOf(item) === idx;
            });
        },
        childEmitSpuItem(data) {
            console.log(data);
            let _this = this;
            _this.isDrawerForSpuVisible = false;
            if (_this.choosenFlag === 'selectSpu') {
                _this.form.spuId = data.spuId;
                _this.form.spuName = data.spuName;
            }
        },
        childEmitItem(data) {
            let _this = this;
            _this.isDrawerVisible = false;
            if (_this.choosenFlag === 'mainImage01') {
                _this.form.mainImage01 = data.fileUrl;
            } else if (_this.choosenFlag === 'mainImage02') {
                _this.form.mainImage02 = data.fileUrl;
            } else if (_this.choosenFlag === 'mainImage03') {
                _this.form.mainImage03 = data.fileUrl;
            }
        },
        setListCommonCode(codeType) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + "/system/code/type/list?codeType=" + codeType).then((response) => {
                _this.lstPostType = response.data.data;
            })
        },
        getSkuInfoBySkuId(skuId) {
            let _this = this;
            _this.$request.get(process.env.VUE_APP_SERVER + '/system/sku/info?skuId=' + skuId)
            .then(response => {
                if (response.data.code === 0) {
                    _this.form = response.data.data;
                } else {
                    _this.$message.error(response.data.msg);
                }
                _this.loading = false;
            })
            .catch(response => {
                alert("Fail");
            });
        },
        handleChooseSpu(flag) {
            let _this = this;
            _this.isDrawerForSpuVisible = true;
            _this.choosenFlag = flag;
        },
        handleChooseMain(flag) {
            let _this = this;
            // _this.loadingDrawer = true;
            _this.isDrawerVisible = true;
            _this.choosenFlag = flag;
        },
        handleDeleteChooseMain(flag) {
            let _this = this;
            if (flag == 'mainImage01') {
                _this.form.mainImage01 = '';
            }
        },
        handleOnSubmit() {
            let _this = this;
            console.log(_this.form);
            _this.$request
                .post(process.env.VUE_APP_SERVER + "/system/sku/proc", _this.form)
                .then(response => {
                    if (response.data.code === 0) {
                        _this.$message.success(response.data.msg);
                        _this.$router.push({name: 'sku/list'});
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