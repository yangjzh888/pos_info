package com.cu.test;

import com.cu.dao.LogDao;
import com.cu.dao.PosDao;
import com.cu.dao.SmsDao;
import com.cu.util.CryptoUtil;

/**
 * 测试类
 */
public class Test {

    public static void main(String[] args) throws Exception {

        /**
         * 测试搜索引擎
         */
        PosDao posDao = new PosDao();

        // 将网络资源系统增量POS数据列表添加到Lucene搜索引擎
        //posDao.getIncrementPosListToLucene();

        // 将网络资源系统全量POS数据列表添加到Lucene搜索引擎
        // posDao.getFullPosListToLucene();

        /**
         * 测试短信
         */
        SmsDao smsDao = new SmsDao();
        smsDao.sendMessage("18501999524", "2021-01-30 分光器资源增量数据更新完成");

        /**
         * 测试日志
         */
        LogDao logDao = new LogDao();
        //logDao.insertLog("分光器索引文件更新","4区局分光器数据增量更新25条",1);
        //System.out.println(LogDao.getLatestDateVersion());
        // System.out.println(logDao.getLogContent("2021-01-30"));

        /**
         * 测试日期
         */
        //Timestamp now = new Timestamp(new Date().getTime());
        //System.out.println(DateUtil.getYesterday(now));

        /**
         * 测试加解密
         */
//        System.out.println(CryptoUtil.encrypt("梅西"));
//        System.out.println(CryptoUtil.encrypt("15611072345"));
//        System.out.println(CryptoUtil.desEncrypt("N1DOupWCpItadmhAWqLP5Q=="));
//        System.out.println(CryptoUtil.desEncrypt("B8HEFb1Ee9ZiBlSlv3xYdA=="));
    }

}
