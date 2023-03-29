package com.mbh.jiudian.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mbh.jiudian.common.R;
import com.mbh.jiudian.dao.RoomOrderDao;
import com.mbh.jiudian.entity.Custom;
import com.mbh.jiudian.entity.OrderDto;
import com.mbh.jiudian.entity.Room;
import com.mbh.jiudian.entity.RoomOrder;
import com.mbh.jiudian.service.CustomService;
import com.mbh.jiudian.service.RoomOrderService;
import com.mbh.jiudian.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomOrderDao roomOrderDao;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CustomService customService;

    @Autowired
    private RoomOrderService roomOrderService;

    @PostMapping("/roomdetails")
    public R<List<Room>> rooms() {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        List<Room> list = roomService.list(queryWrapper);

        return R.success(list);
    }

    @PostMapping("/mine")
    public R<Custom> mine(HttpServletRequest request, HttpServletResponse response) {

        Integer id = (Integer) request.getServletContext().getAttribute("custom");

        LambdaQueryWrapper<Custom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Custom::getCid, id);

        Custom custom = customService.getOne(queryWrapper);

        return R.success(custom);
    }

    @PostMapping("/selectorder")
    public R<List<OrderDto>> Orders(HttpServletRequest request,RoomOrder order) {

        Integer id = (Integer) request.getServletContext().getAttribute("custom");

        LambdaQueryWrapper<Custom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Custom::getCid,id);
        Custom custom = customService.getOne(queryWrapper);

        List<OrderDto> orderDtos = roomOrderDao.selectOrders(custom.getCid());

        return R.success(orderDtos);

    }

    @PostMapping("/insertorder")
    public R<String> insertorder(RoomOrder order,HttpServletRequest request){

        Integer id = (Integer) request.getServletContext().getAttribute("custom");

        String today = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,0);
        Date date1 = calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStringYYYYMMDD1 = format1.format(date1);
        today = dateStringYYYYMMDD1;


        String tomorrow = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date date2 = cal.getTime();
        SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD2 = format2.format(date2);
        tomorrow = dateStringYYYYMMDD2;

        order.setStarttime(today);
        order.setEndtime(tomorrow + " 12:00:00");
        order.setCid(id);
        order.setOrderstate(0);
        roomOrderService.save(order);

        return R.success("预订成功");


    }
}

