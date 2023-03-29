package com.mbh.jiudian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mbh.jiudian.entity.OrderDto;
import com.mbh.jiudian.entity.RoomOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomOrderDao extends BaseMapper<RoomOrder> {

//     @Select("select room.rid,room.roomdesc,room_order.orderid,custom.cname,custom.cphone,room.price from custom,room,room_order where custom.cid = room_order.cid and room.rid = room_order.rid;")
     List<OrderDto> selectOrders(@Param("cid") Integer cid);

     void saveOrders(@Param("cid") Integer cid,@Param("rid") Integer rid);
}


