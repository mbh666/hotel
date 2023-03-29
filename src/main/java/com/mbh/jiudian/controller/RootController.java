package com.mbh.jiudian.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mbh.jiudian.common.R;
import com.mbh.jiudian.dao.RootDao;
import com.mbh.jiudian.entity.Custom;
import com.mbh.jiudian.entity.Room;
import com.mbh.jiudian.entity.RoomOrder;
import com.mbh.jiudian.entity.Root;
import com.mbh.jiudian.service.CustomService;
import com.mbh.jiudian.service.RoomOrderService;
import com.mbh.jiudian.service.RoomService;
import com.mbh.jiudian.service.RootSerice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    private RootSerice rootSerice;

    @Autowired
    private RootDao rootDao;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomOrderService orderService;

    @Autowired
    private CustomService customService;

    @PostMapping("/root")
    public R<Root> root(HttpServletRequest request, HttpServletResponse response, Root root) throws ServletException, IOException {

//        Long id = (Long) request.getSession().getAttribute("root");
         Integer id = (Integer) request.getServletContext().getAttribute("root");

//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper<>();

//        queryWrapper.eq(Root::getRootid,id);

//        Root r = rootSerice.getOne(queryWrapper);

//        Root r = rootDao.selectById(id);
//////
//        String password = r.getPassword();
////
//        if(r == null){
//            return R.nofind("您不是管理员");
//        }
//
//        if(!r.getPassword().equals(password)){
//            return R.error("登录失败");
//        }
        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Root::getRootid,id);
        root = rootSerice.getOne(queryWrapper);

        return R.success(root);

    }

    @PostMapping("/allrooms")
    public R<List<Room>> room(HttpServletRequest request,HttpServletResponse response) throws IOException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));
//        response.sendRedirect("../allrooms");

        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        List<Room> list = roomService.list(wrapper);
        return R.success(list);
//        return R.success(root);

    }

    @PostMapping("/addroom")
    public R<String> save(Room room){
        roomService.save(room);
        return R.success("新增房间成功");
    }



    @PostMapping("/updateroom")
    public R<String> update(Room room,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> wrapper = new LambdaQueryWrapper();
//        Root root = rootSerice.getOne(wrapper.eq(Root::getRootid,id));
        System.out.println(room);
        roomService.updateById(room);

        return R.success("修改房间成功");
    }

    @PostMapping("/updateroomstate")
    public R<String> updatestate(Room room,HttpServletRequest request,HttpServletResponse response){

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> wrapper = new LambdaQueryWrapper();
//        Root root = rootSerice.getOne(wrapper.eq(Root::getRootid,id));

        roomService.updateById(room);
        return R.success("修改房间状态成功");
    }

    @PostMapping("/deleteroom")
    public R<String> delete(Room room){

        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Room::getRid,room.getRid());
        roomService.remove(queryWrapper);
        return R.success("删除房间成功");
    }

    @PostMapping("/allorders")
    public R<List<RoomOrder>> order(HttpServletRequest request,HttpServletResponse response) throws IOException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));
//        response.sendRedirect("../allorders");
//        return R.success(root);
        LambdaQueryWrapper<RoomOrder> queryWrapper = new LambdaQueryWrapper<>();
        List<RoomOrder> list = orderService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/checkin")
    public R<String> checkin(RoomOrder order,HttpServletRequest request,HttpServletResponse response){
//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));

        order.setOrderstate(1);
        orderService.updateById(order);
        return R.success("操作成功");
    }

    @PostMapping("/checkoutorder")
    public R<String> checkoutorder(RoomOrder order,HttpServletRequest request,HttpServletResponse response){
//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));

        order.setOrderstate(2);
        orderService.updateById(order);
        return R.success("操作成功");
    }

    @PostMapping("/confdelete")
    public R<String> confdelete(HttpServletRequest request,HttpServletResponse response,RoomOrder order) throws IOException {
//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));
        LambdaQueryWrapper<RoomOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoomOrder::getOrderid,order.getOrderid());
        orderService.remove(wrapper);
//        response.sendRedirect("../confdelete");
        return R.success("操作成功");
    }

    @PostMapping("/getusers")
    public R<List<Custom>> custom(HttpServletRequest request,HttpServletResponse response) throws IOException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));
//        response.sendRedirect("../getusers");
//        return R.success(root);
        LambdaQueryWrapper<Custom> queryWrapper = new LambdaQueryWrapper<>();
        List<Custom> list = customService.list(queryWrapper);
        return R.success(list);

    }

    @PostMapping("/freeze")
    public R<String> freeze(Custom custom, HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));

        custom.setAuthentication("2");
        customService.updateById(custom);
        return R.success("操作成功");

    }
    @PostMapping("/nofreeze")
    public R<String> nofreeze(Custom custom, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));

        LambdaQueryWrapper<Custom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Custom::getCid,custom.getCid());
        Custom c = customService.getOne(wrapper);
        if(c.getCname() != null&&c.getCphone() != null&&c.getCcardid()!=null){
            custom.setAuthentication("0");
        }else
            custom.setAuthentication("1");
        customService.updateById(custom);
        return R.success("操作成功");

    }

    @PostMapping("/deletecustom")
    public R<String> deletecustom(Custom custom, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        Integer id = (Integer) request.getServletContext().getAttribute("root");
//
//        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper();
//
//        Root root = rootSerice.getOne(queryWrapper.eq(Root::getRootid,id));

        customService.removeById(custom);
        return R.success("操作成功");

    }
}
