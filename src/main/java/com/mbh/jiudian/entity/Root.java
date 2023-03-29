package com.mbh.jiudian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Root implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer rootid;
    @TableField(value = "rootname")
    private String rootname;
    @TableField(value = "rootpassword")
    private String password;
    @TableField(value = "rootstate")
    private Integer rootstate;

}
