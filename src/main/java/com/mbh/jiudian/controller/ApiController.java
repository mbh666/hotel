package com.mbh.jiudian.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mbh.jiudian.common.R;
import com.mbh.jiudian.entity.Custom;
import com.mbh.jiudian.entity.Root;
import com.mbh.jiudian.service.CustomService;
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

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CustomService customService;

    @Autowired
    private RootSerice rootSerice;

    @PostMapping("/login")
    public R<Custom> login(HttpServletRequest request, Custom custom){

        java.lang.String password = custom.getPassword();
//        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Custom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Custom::getUsername,custom.getUsername());
        Custom cus = customService.getOne(queryWrapper);

        if(cus == null){
            return R.nofind("账号未注册");
        }

        if(!cus.getPassword().equals(password)){
            return R.error("登录失败");
        }

        request.getServletContext().setAttribute("custom",cus.getCid());
        return R.success(cus);
    }

    @PostMapping("/rootlogin")
    public R<Root> rootlogin(HttpServletRequest request, HttpServletResponse response,Root root) throws ServletException, IOException {

        java.lang.String password = root.getPassword();


        LambdaQueryWrapper<Root> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Root::getRootname,root.getRootname());
        Root r = rootSerice.getOne(queryWrapper);

        if(r == null){
            return R.nofind("您不是管理员");
        }

        if(!r.getPassword().equals(password)){
            return R.error("登录失败");
        }
//        request.getSession().setAttribute("root",r.getRootid());
        request.getServletContext().setAttribute("root",r.getRootid());
        return R.success(r);
    }

    @PostMapping("/register")
    public R<String> register(Custom custom){
        customService.save(custom);
        return R.success("注册成功");
    }

    @PostMapping("/insertauth")
    public R<String> insert(Custom custom, String statu){

        statu = "1";

        if(custom.getCphone() != null&&custom.getCcardid() != null&&custom.getCname() != null){
            statu = "0";
        }
        custom.setAuthentication(statu);
        customService.updateById(custom);
        return R.success("认证成功");
    }

    @PostMapping("/changepwd")
    public R<String> changepwd(Custom custom){

//        String newpwd = custom.getPassword();
//        custom.setPassword(newpwd);
        customService.updateById(custom);
        return R.success("修改密码成功");
    }
}
