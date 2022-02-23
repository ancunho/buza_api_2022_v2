package com.buza.server.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TbSpec {
    private Integer specSeq;

    private Integer productSeq;

    private String productName;

    private BigDecimal price;

    private BigDecimal vipPrice;

    private String weight;

    private String stock;

    private BigDecimal deliveryPrice;

    private String deliveryType;

    private String packageCode;

    private String useYn;

    private Integer status;

    private String area;

    private String country;

    private String option01;

    private String option02;

    private String option03;

    private String option04;

    private String option05;

    private Date createtime;

    private Date updatetime;

    public Integer getSpecSeq() {
        return specSeq;
    }

    public void setSpecSeq(Integer specSeq) {
        this.specSeq = specSeq;
    }

    public Integer getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(Integer productSeq) {
        this.productSeq = productSeq;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", specSeq=").append(specSeq);
        sb.append(", productSeq=").append(productSeq);
        sb.append(", productName=").append(productName);
        sb.append(", price=").append(price);
        sb.append(", vipPrice=").append(vipPrice);
        sb.append(", weight=").append(weight);
        sb.append(", stock=").append(stock);
        sb.append(", deliveryPrice=").append(deliveryPrice);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", packageCode=").append(packageCode);
        sb.append(", useYn=").append(useYn);
        sb.append(", status=").append(status);
        sb.append(", area=").append(area);
        sb.append(", country=").append(country);
        sb.append(", option01=").append(option01);
        sb.append(", option02=").append(option02);
        sb.append(", option03=").append(option03);
        sb.append(", option04=").append(option04);
        sb.append(", option05=").append(option05);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}