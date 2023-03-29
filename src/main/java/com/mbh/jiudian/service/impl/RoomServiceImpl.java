package com.mbh.jiudian.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbh.jiudian.dao.RoomDao;
import com.mbh.jiudian.entity.Room;
import com.mbh.jiudian.service.RoomService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomDao,Room> implements RoomService {
    @Override
    public void roomUpdate(Room room) {

    }
}




