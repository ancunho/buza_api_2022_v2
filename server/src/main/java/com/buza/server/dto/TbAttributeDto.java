package com.buza.server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TbAttributeDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer attrId;
    private String attrName;
    private String attrType;
    private String status;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;
    private String createTime;
    private String updateTime;

}