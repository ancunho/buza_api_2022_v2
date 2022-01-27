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
public class TbCustomerDto extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer customerId;
    private String weixinOpenId;
    private String weixinUnionId;
    private String username;
    private String password;
    private String mobileNo;
    private String nickname;
    private String grade;
    private String customerType;
    private String realname;
    private String birthday;
    private String sex;
    private String email;
    private String country;
    private String province;
    private String city;
    private String address;
    private String status;
    private String statusName;
    private String wechatId;
    private String option01;
    private String option02;
    private String option03;
    private String option04;
    private String option05;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

}