package com.buza.server.entity;

import java.util.Date;

public class TbSpu {
    private Integer spuId;

    private Integer categoryId;

    private Integer brandId;

    private String spuName;

    private String spuType;

    private String status;

    private String mainImage01;

    private String mainImage02;

    private String mainImage03;

    private String subImage01;

    private String subImage02;

    private String subImage03;

    private String subImage04;

    private String subImage05;

    private String option01;

    private String option02;

    private String option03;

    private String option04;

    private String option05;

    private Date createTime;

    private Date updateTime;

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuType() {
        return spuType;
    }

    public void setSpuType(String spuType) {
        this.spuType = spuType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMainImage01() {
        return mainImage01;
    }

    public void setMainImage01(String mainImage01) {
        this.mainImage01 = mainImage01;
    }

    public String getMainImage02() {
        return mainImage02;
    }

    public void setMainImage02(String mainImage02) {
        this.mainImage02 = mainImage02;
    }

    public String getMainImage03() {
        return mainImage03;
    }

    public void setMainImage03(String mainImage03) {
        this.mainImage03 = mainImage03;
    }

    public String getSubImage01() {
        return subImage01;
    }

    public void setSubImage01(String subImage01) {
        this.subImage01 = subImage01;
    }

    public String getSubImage02() {
        return subImage02;
    }

    public void setSubImage02(String subImage02) {
        this.subImage02 = subImage02;
    }

    public String getSubImage03() {
        return subImage03;
    }

    public void setSubImage03(String subImage03) {
        this.subImage03 = subImage03;
    }

    public String getSubImage04() {
        return subImage04;
    }

    public void setSubImage04(String subImage04) {
        this.subImage04 = subImage04;
    }

    public String getSubImage05() {
        return subImage05;
    }

    public void setSubImage05(String subImage05) {
        this.subImage05 = subImage05;
    }

    public String getOption01() {
        return option01;
    }

    public void setOption01(String option01) {
        this.option01 = option01;
    }

    public String getOption02() {
        return option02;
    }

    public void setOption02(String option02) {
        this.option02 = option02;
    }

    public String getOption03() {
        return option03;
    }

    public void setOption03(String option03) {
        this.option03 = option03;
    }

    public String getOption04() {
        return option04;
    }

    public void setOption04(String option04) {
        this.option04 = option04;
    }

    public String getOption05() {
        return option05;
    }

    public void setOption05(String option05) {
        this.option05 = option05;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", spuId=").append(spuId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append(", spuName=").append(spuName);
        sb.append(", spuType=").append(spuType);
        sb.append(", status=").append(status);
        sb.append(", mainImage01=").append(mainImage01);
        sb.append(", mainImage02=").append(mainImage02);
        sb.append(", mainImage03=").append(mainImage03);
        sb.append(", subImage01=").append(subImage01);
        sb.append(", subImage02=").append(subImage02);
        sb.append(", subImage03=").append(subImage03);
        sb.append(", subImage04=").append(subImage04);
        sb.append(", subImage05=").append(subImage05);
        sb.append(", option01=").append(option01);
        sb.append(", option02=").append(option02);
        sb.append(", option03=").append(option03);
        sb.append(", option04=").append(option04);
        sb.append(", option05=").append(option05);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}