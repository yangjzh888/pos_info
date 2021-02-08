package com;


import com.cu.dao.PosDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        PosDao posDao = new PosDao();
        // posDao.insertPosBeizhu(1,"admin",new Timestamp(new Date().getTime()), "分光器信息有误");
        // posDao.getlatestPosList();
        // System.out.println(posDao.getPosList());
        // System.out.println(UUID.randomUUID().toString());
    }
}
