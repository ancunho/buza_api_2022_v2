package com.buza.server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class TbSpuDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer spuId;
    private Integer categoryId;
    private Integer brandId;
    private String spuName;
    private String spuType;
    private String status;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;
    private String createTime;
    private String updateTime;



}
