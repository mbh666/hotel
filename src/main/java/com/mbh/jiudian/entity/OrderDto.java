package com.mbh.jiudian.entity;

import lombok.Data;

@Data
public class OrderDto {

    private String imgURL;

    private Integer rid;

    private String roomdesc;

    private Integer ordernumber;

    private String realname;

    private String phone;

    private String price;

    private String starttime;

    private String endtime;
}
