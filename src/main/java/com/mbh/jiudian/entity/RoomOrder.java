package com.mbh.jiudian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
public class RoomOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer orderid;

    private String starttime;

    private String endtime;

    private Integer cid;

    private Integer rid;

    private Integer orderstate;
}
