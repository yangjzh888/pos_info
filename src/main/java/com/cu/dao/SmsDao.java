package com.cu.dao;

import com.cu.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 短信数据接口
 */
public class SmsDao {

    /**
     * 发送短信
     * @return
     */
    public String sendMessage(String phoneNumber, String message){
        // 初始化短信网关数据库连接/执行/结果集
        Connection connSM = null;
        PreparedStatement psSM = null;

        //SQL语句:发送短信
        String sqlSendMessage = "INSERT INTO send_alarm_sms(phonenumber,message,send_flag) VALUES(?, ?, 0)";

        try{
            // 短信网关数据库连接/执行/结果集
            connSM = JdbcUtil.getSMConn();
            psSM = connSM.prepareStatement(sqlSendMessage);
            psSM.setString(1, phoneNumber);
            psSM.setString(2, message);
            psSM.execute();
            // System.out.println("[SMS]" + phoneNumber + "发送成功");
            JdbcUtil.closeStmt(psSM);
            JdbcUtil.closeConn(connSM);
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        finally {
            // 关闭网络资源数据库连接/执行/结果集
            JdbcUtil.closeStmt(psSM);
            JdbcUtil.closeConn(connSM);
        }
        return "success";
    }




}
