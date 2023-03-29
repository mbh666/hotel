package com.mbh.jiudian;

import com.mbh.jiudian.common.R;
import com.mbh.jiudian.dao.RoomOrderDao;
import com.mbh.jiudian.entity.OrderDto;
import com.mbh.jiudian.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.xml.ws.WebEndpoint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
class JiudianApplicationTests {

    @Autowired
    private RoomOrderDao roomOrderDao;

    @Test
    public void lastday(){
        String today = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,0);
        Date date1 = calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStringYYYYMMDD1 = format1.format(date1);
        today = dateStringYYYYMMDD1;
        System.out.println(today);


        String tomorrow = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date date2 = cal.getTime();
        SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD2 = format2.format(date2);
        tomorrow = dateStringYYYYMMDD2;
        System.out.println(tomorrow);
    }


}
