package com.mbh.jiudian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbh.jiudian.dao.CustomDao;
import com.mbh.jiudian.entity.Custom;
import com.mbh.jiudian.service.CustomService;
import org.springframework.stereotype.Service;

@Service
public class CustomServiceImpl extends ServiceImpl<CustomDao, Custom> implements CustomService {
}
