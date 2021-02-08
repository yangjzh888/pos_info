package com.cu.dao;

import com.cu.entity.Beizhu;
import com.cu.entity.Pos;
import com.cu.util.JdbcUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * POS备注数据接口
 */
@Component
public class BeizhuDao {

    /**
     * 在能力中台数据库插入POS备注
     * @param pos 分光器对象
     * @param bei_zhu 备注
     * @param caozuo_yuan 操作员
     *
     * @return
     */
    public String commitBeizhu(Pos pos, String bei_zhu, String caozuo_yuan, String caozuo_yuan_dianhua){

        // 数据库连接/执行/结果集
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL语句:插入POS备注
        String sql = "INSERT nlsc_pos_beizhu(beizhu_id, pos_id, pos_bianhao, louyu_mingcheng," +
                "weizhi_leixing, wei_zhi, anzhuang_weizhi, qu_ju, pon_biaoshi, biao_qian," +
                "fenxian_he, olt_mingcheng, cao_lu, pon_duankou_hao, olt_ip_dizhi, olt_leixing," +
                "chang_jia, bei_zhu, caozuo_yuan, caozuo_yuan_dianhua, caozuo_shijian, shenhe_zhuangtai) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0);";

        try {
            conn = JdbcUtil.getZTConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setInt(2, pos.getPos_id());
            ps.setString(3, pos.getPos_bianhao());
            ps.setString(4, pos.getLouyu_mingcheng());
            ps.setString(5,pos.getWeizhi_leixing());
            ps.setString(6,pos.getWei_zhi());
            ps.setString(7, pos.getAnzhuang_weizhi());
            ps.setString(8, pos.getQu_ju());
            ps.setString(9, pos.getPon_biaoshi());
            ps.setString(10, pos.getBiao_qian());
            ps.setString(11, pos.getFenxian_he());
            ps.setString(12, pos.getOlt_mingcheng());
            ps.setInt(13, pos.getCao_lu());
            ps.setInt(14, pos.getPon_duankou_hao());
            ps.setString(15, pos.getOlt_ip_dizhi());
            ps.setString(16, pos.getOlt_leixing());
            ps.setString(17, pos.getChang_jia());
            ps.setString(18, bei_zhu);
            ps.setString(19, caozuo_yuan);
            ps.setString(20, caozuo_yuan_dianhua);
            ps.setTimestamp(21, new Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closeStmt(ps);
            JdbcUtil.closeConn(conn);
        }
        return "success";
    }


    /**
     * 根据审核状态获取POS备注列表
     * @param shenhe_zhuangtai 审核状态(1:已审核、0:未审核)
     *
     * @return
     */
    public List<Beizhu> getBeizhuList(int shenhe_zhuangtai){
        // 数据库连接/执行/结果集
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] shijian = new String[]{"caozuo_shijian","shenhe_shijian"};

        // SQL语句:根据审核状态获取POS备注列表
        String sql = "SELECT " +
                "beizhu_id," +
                "pos_id," +
                "pos_bianhao," +
                "louyu_mingcheng," +
                "weizhi_leixing," +
                "wei_zhi," +
                "anzhuang_weizhi," +
                "qu_ju," +
                "pon_biaoshi," +
                "biao_qian," +
                "fenxian_he," +
                "olt_mingcheng," +
                "cao_lu," +
                "pon_duankou_hao," +
                "olt_ip_dizhi," +
                "olt_leixing," +
                "chang_jia," +
                "bei_zhu," +
                "caozuo_yuan," +
                "caozuo_yuan_dianhua," +
                "caozuo_shijian," +
                "shenhe_yuan," +
                "shenhe_shijian " +
                "FROM nlsc_pos_beizhu b " +
                "WHERE b.shenhe_zhuangtai = ? " +
                "ORDER BY " + shijian[shenhe_zhuangtai]+" DESC";

        List<Beizhu> beizhuList = new ArrayList<Beizhu>();

        try {
            conn = JdbcUtil.getZTConn();
            ps = conn.prepareStatement(sql);

            // 载入参数
            ps.setInt(1, shenhe_zhuangtai);
            rs = ps.executeQuery();

            while (rs.next()) {
                Beizhu beizhu = new Beizhu();
                Pos pos_beizhu = new Pos();
                beizhu.setBeizhu_id(rs.getString("beizhu_id"));
                pos_beizhu.setPos_id(rs.getInt("pos_id"));
                pos_beizhu.setPos_bianhao(rs.getString("pos_bianhao"));
                pos_beizhu.setLouyu_mingcheng(rs.getString("louyu_mingcheng"));
                pos_beizhu.setWeizhi_leixing(rs.getString("weizhi_leixing"));
                pos_beizhu.setWei_zhi(rs.getString("wei_zhi"));
                pos_beizhu.setAnzhuang_weizhi(rs.getString("anzhuang_weizhi"));
                pos_beizhu.setQu_ju(rs.getString("qu_ju"));
                pos_beizhu.setPon_biaoshi(rs.getString("pon_biaoshi"));
                pos_beizhu.setBiao_qian(rs.getString("biao_qian"));
                pos_beizhu.setFenxian_he(rs.getString("fenxian_he"));
                pos_beizhu.setOlt_mingcheng(rs.getString("olt_mingcheng"));
                pos_beizhu.setCao_lu(rs.getInt("cao_lu"));
                pos_beizhu.setPon_duankou_hao(rs.getInt("pon_duankou_hao"));
                pos_beizhu.setOlt_ip_dizhi(rs.getString("olt_ip_dizhi"));
                pos_beizhu.setOlt_leixing(rs.getString("olt_leixing"));
                pos_beizhu.setChang_jia(rs.getString("chang_jia"));
                beizhu.setPos(pos_beizhu);
                beizhu.setBei_zhu(rs.getString("bei_zhu"));
                beizhu.setCaozuo_yuan(rs.getString("caozuo_yuan"));
                beizhu.setCaozuo_yuan_dianhua(rs.getString("caozuo_yuan_dianhua"));
                beizhu.setCaozuo_shijian(rs.getTimestamp("caozuo_shijian"));
                beizhu.setShenhe_yuan(rs.getString("shenhe_yuan"));
                beizhu.setShenhe_shijian(rs.getTimestamp("shenhe_shijian"));
                beizhuList.add(beizhu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closeStmt(ps);
            JdbcUtil.closeConn(conn);
        }
        return beizhuList;
    }


    /**
     * 在能力中台数据库更新POS备注
     * @param beizhu_id 备注ID
     * @param shenhe_yuan 操作员
     *
     * @return
     */
    public String checkBeizhu(String beizhu_id, int shenhe_zhuangtai, String shenhe_yuan){

        // 数据库连接/执行/结果集
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL语句:更新POS备注的审核状态
        String sql = "UPDATE nlsc_pos_beizhu " +
                "SET shenhe_zhuangtai = ?," +
                "   shenhe_yuan = ?," +
                "   shenhe_shijian = ? "+
                "WHERE beizhu_id = ?;";
        try {
            conn = JdbcUtil.getZTConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shenhe_zhuangtai);
            ps.setString(2, shenhe_yuan);
            ps.setTimestamp(3, new Timestamp(new Date().getTime()));
            ps.setString(4, beizhu_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closeStmt(ps);
            JdbcUtil.closeConn(conn);
        }
        return "success";
    }
}
