package com.cu.dao;

import com.cu.util.DateUtil;
import com.cu.util.JdbcUtil;
import com.cu.util.PropertyUtil;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

/**
 * 日志数据接口
 */
public class LogDao {


    /**
     * 新增日志
     * @param log_type 日志类型
     * @param log_content 日志内容
     * @param log_status 日志状态(1:成功; 2:失败)
     * @return
     */
    public String insertLog(String log_type, String log_content, int log_status){

        // 网络能力中台数据库连接/执行
        Connection connZT = null;
        PreparedStatement psZT = null;

        // SQL语句:插入日志
        String sqlInsertLog = "INSERT nlsc_pos_log" +
                "(log_id, log_type, log_content, log_time, log_status, log_ip) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            connZT = JdbcUtil.getZTConn();
            psZT = connZT.prepareStatement(sqlInsertLog);
            psZT.setString(1, UUID.randomUUID().toString());
            psZT.setString(2, log_type);
            psZT.setString(3, log_content);
            psZT.setTimestamp(4, new Timestamp(new Date().getTime()));
            psZT.setInt(5, log_status);
            psZT.setString(6, PropertyUtil.getPropertyByKey("server_ip"));
            psZT.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } finally {
            JdbcUtil.closeStmt(psZT);
            JdbcUtil.closeConn(connZT);
        }
        return "success";
    }

    /**
     * 获取日志表中状态为成功的最新版本
     * @return
     */
    public static String getLatestDateVersion(){
        // 网络能力中台数据库连接/执行
        Connection connZT = null;
        PreparedStatement psZT = null;
        ResultSet rsZT = null;

        // SQL语句:插入日志
        String sqlGetversion = "SELECT log_time" +
                " FROM nlsc_pos_log" +
                " WHERE log_status = 1 " +
                " AND log_ip = ? " +
                " ORDER BY log_time DESC " +
                " LIMIT 1";
        String version = DateUtil.getYesterday(new Timestamp(new Date().getTime()));

        try {
            connZT = JdbcUtil.getZTConn();
            psZT = connZT.prepareStatement(sqlGetversion);
            psZT.setString(1, PropertyUtil.getPropertyByKey("server_ip"));
            rsZT = psZT.executeQuery();
            while (rsZT.next()){
                 version = DateUtil.getYesterday(rsZT.getTimestamp(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return DateUtil.getYesterday(new Timestamp(new Date().getTime()));
        } finally {
            JdbcUtil.closeStmt(psZT);
            JdbcUtil.closeConn(connZT);
        }
        return version;
    }

    /**
     * 获取日志表中状态为成功的最新内容
     * @return
     */
    public String getLogContent(String log_date){
        // 网络能力中台数据库连接/执行
        Connection connZT = null;
        PreparedStatement psZT = null;
        ResultSet rsZT = null;

        // SQL语句:插入日志
        String sqlGetversion = "SELECT log_type, log_content " +
                " FROM nlsc_pos_log " +
                " WHERE log_status=1 " +
                " AND DATE_FORMAT(log_time,'%Y-%m-%d') = ? " +
                " AND log_ip = ? " +
                " ORDER BY log_time DESC " +
                " LIMIT 1";
        String result = "";
        try {
            connZT = JdbcUtil.getZTConn();
            psZT = connZT.prepareStatement(sqlGetversion);
            psZT.setString(1, log_date);
            psZT.setString(2, PropertyUtil.getPropertyByKey("server_ip"));
            rsZT = psZT.executeQuery();
            while (rsZT.next()){
                result += ("[" + rsZT.getString("log_type") + "] ");
                result += rsZT.getString("log_content");
            }
            if (result.equals("")){
                return "未找到4区局分光器数据增量更新日志";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "4区局分光器数据增量更新失败";
        } finally {
            JdbcUtil.closeStmt(psZT);
            JdbcUtil.closeConn(connZT);
        }
        return result;
    }


}
