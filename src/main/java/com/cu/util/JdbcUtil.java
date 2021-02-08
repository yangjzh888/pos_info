package com.cu.util;

import org.springframework.beans.factory.annotation.Value;


import java.sql.*;

/**
 * 数据库连接工具类
 */
public class JdbcUtil {
    @Value("${serv.ip}")
    private String ip;

    // 生成能力中台MySQL数据库连接
    public static Connection getZTConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String mysqlIp = PropertyUtil.getPropertyByKey("mysql.ip");
            String mysqlPort = PropertyUtil.getPropertyByKey("mysql.port");
            String mysqlUser = PropertyUtil.getPropertyByKey("mysql.username");
            String mysqlPass = PropertyUtil.getPropertyByKey("mysql.password");
            String mysqlDB = PropertyUtil.getPropertyByKey("mysql.db");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + mysqlIp + ":" + mysqlPort + "/"+mysqlDB+"?" +
                            "useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8",
                    mysqlUser, mysqlPass);
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://132.91.175.98:8166/g41_wyzx_nlztdb?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8",
//                    "nlztdatabase", "B6.nlztdatabase312");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 生成资源系统Oracle数据库连接
    public static Connection getZYConn() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@132.77.64.113:1521/jxdb.oracle.com", "gxkf", "lxyygxkf");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 生成短信网关Oracle数据库连接
    public static Connection getSMConn() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@132.77.64.202:1522/ifmdb", "sms", "sms");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭数据库连接
    public static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭SQL语句发送
    public static void closeStmt(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭结果集
    public static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        System.out.println(ip);
    }

    public static void main(String[] args) {
        new JdbcUtil().test();
    }


}
