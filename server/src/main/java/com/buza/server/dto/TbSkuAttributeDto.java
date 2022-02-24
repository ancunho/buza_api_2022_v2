package com.buza.server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TbSkuAttributeDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer skuAttrId;
    private Integer skuId;
    private Integer attrValueId;
    private String status;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;
    private String createTime;
    private String updateTime;
}
