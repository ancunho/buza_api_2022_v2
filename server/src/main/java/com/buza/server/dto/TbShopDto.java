package com.buza.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class TbShopDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer shopId;
    private String shopName;
    private String shopIntro;
    private String managerName;
    private String managerMobile;
    private String shopMobile;
    private String shopStatus;
    private String statusName;
    private String shopProvince;
    private String shopCity;
    private String shopDistrict;
    private String shopAddress;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

}