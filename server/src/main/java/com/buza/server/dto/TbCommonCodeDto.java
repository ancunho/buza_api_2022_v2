package com.buza.server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class TbCommonCodeDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codeId;
    private String codeType;
    private String codeCd;
    private String codeName;
    private String useYn;
    private String remark;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;
    private Date createTime;
    private Date updateTime;
}
