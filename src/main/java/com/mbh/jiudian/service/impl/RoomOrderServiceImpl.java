package com.mbh.jiudian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbh.jiudian.dao.RoomOrderDao;
import com.mbh.jiudian.entity.RoomOrder;
import com.mbh.jiudian.service.RoomOrderService;
import org.springframework.stereotype.Service;

@Service
public class RoomOrderServiceImpl extends ServiceImpl<RoomOrderDao, RoomOrder> implements RoomOrderService {
}
