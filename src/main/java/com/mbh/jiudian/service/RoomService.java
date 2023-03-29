package com.mbh.jiudian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mbh.jiudian.entity.Room;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;

import java.util.List;

public interface RoomService extends IService<Room> {
    void roomUpdate(Room room);
}
