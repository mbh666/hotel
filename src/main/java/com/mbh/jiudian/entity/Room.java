package com.mbh.jiudian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer rid;

    private Integer rtype;

    private Integer rstate;

    private String price;

    private String roomdesc;
    @TableField(value = "imgUrl")
    private String imgURL;
}
