package com.cu.schedule;

import com.cu.dao.LogDao;
import com.cu.dao.PosDao;
import com.cu.dao.SmsDao;
import com.cu.util.PropertyUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduledTask {


    /***
     * 分光器增量数据更新(每日凌晨1时0分)
     */
    //@Scheduled(fixedRate=5000)
    @Scheduled(cron = "${update_time}")
    private void incrementUpdate(){
        PosDao posDao = new PosDao();
        posDao.getIncrementPosListToLucene();
    }

    /***
     * 数据更新结果推送短信(每日上午7时10分)
     */
    @Scheduled(cron = "${message_time}")
    private void sendMessage(){
        String server_ip = PropertyUtil.getPropertyByKey("server_ip");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String log_date = sdf.format(date);
        LogDao logDao = new LogDao();
        String log_content = logDao.getLogContent(log_date);
        SmsDao smsDao = new SmsDao();
        String personsTr = PropertyUtil.getPropertyByKey("person");
        String[] personArr = personsTr.split(",");
        for(int i = 0; i < personArr.length; i++){
            smsDao.sendMessage(personArr[i], server_ip + "服务器: " + log_content);
        }


    }




}


