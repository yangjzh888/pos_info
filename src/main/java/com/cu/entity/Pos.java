package com.cu.entity;

import java.sql.Timestamp;

public class Pos {
    // POS_ID
    private int pos_id;
    // POS编号
    private String pos_bianhao;
    // 楼宇名称
    private String louyu_mingcheng;
    // 安装位置
    private String anzhuang_weizhi;
    // 位置类型
    private String weizhi_leixing;
    // 位置
    private String wei_zhi;
    // 区局
    private String qu_ju;
    // 上联标识
    private String pon_biaoshi;
    // 二维码编号
    private String biao_qian;
    // 分纤盒
    private String fenxian_he;
    // OLT名称
    private String olt_mingcheng;
    // 槽路
    private int cao_lu;
    // 端口号
    private int pon_duankou_hao;
    // OLT_IP地址
    private String olt_ip_dizhi;
    // OLT类型
    private String olt_leixing;
    // 厂家
    private String chang_jia;
    // 更新时间
    private Timestamp gengxin_shijian;

    public int getPos_id() {
        return pos_id;
    }

    public void setPos_id(int pos_id) {
        this.pos_id = pos_id;
    }

    public String getPos_bianhao() {
        return pos_bianhao;
    }

    public void setPos_bianhao(String pos_bianhao) {
        this.pos_bianhao = pos_bianhao;
    }

    public String getLouyu_mingcheng() {
        return louyu_mingcheng;
    }

    public void setLouyu_mingcheng(String louyu_mingcheng) {
        this.louyu_mingcheng = louyu_mingcheng;
    }

    public String getAnzhuang_weizhi() {
        return anzhuang_weizhi;
    }

    public void setAnzhuang_weizhi(String anzhuang_weizhi) {
        this.anzhuang_weizhi = anzhuang_weizhi;
    }

    public String getWeizhi_leixing() {
        return weizhi_leixing;
    }

    public void setWeizhi_leixing(String weizhi_leixing) {
        this.weizhi_leixing = weizhi_leixing;
    }

    public String getWei_zhi() {
        return wei_zhi;
    }

    public void setWei_zhi(String wei_zhi) {
        this.wei_zhi = wei_zhi;
    }

    public String getQu_ju() {
        return qu_ju;
    }

    public void setQu_ju(String qu_ju) {
        this.qu_ju = qu_ju;
    }

    public String getPon_biaoshi() {
        return pon_biaoshi;
    }

    public void setPon_biaoshi(String pon_biaoshi) {
        this.pon_biaoshi = pon_biaoshi;
    }

    public String getBiao_qian() {
        return biao_qian;
    }

    public void setBiao_qian(String biao_qian) {
        this.biao_qian = biao_qian;
    }

    public String getFenxian_he() {
        return fenxian_he;
    }

    public void setFenxian_he(String fenxian_he) {
        this.fenxian_he = fenxian_he;
    }

    public String getOlt_mingcheng() {
        return olt_mingcheng;
    }

    public void setOlt_mingcheng(String olt_mingcheng) {
        this.olt_mingcheng = olt_mingcheng;
    }

    public int getCao_lu() {
        return cao_lu;
    }

    public void setCao_lu(int cao_lu) {
        this.cao_lu = cao_lu;
    }

    public int getPon_duankou_hao() {
        return pon_duankou_hao;
    }

    public void setPon_duankou_hao(int pon_duankou_hao) {
        this.pon_duankou_hao = pon_duankou_hao;
    }

    public String getOlt_ip_dizhi() {
        return olt_ip_dizhi;
    }

    public void setOlt_ip_dizhi(String olt_ip_dizhi) {
        this.olt_ip_dizhi = olt_ip_dizhi;
    }

    public String getOlt_leixing() {
        return olt_leixing;
    }

    public void setOlt_leixing(String olt_leixing) {
        this.olt_leixing = olt_leixing;
    }

    public String getChang_jia() {
        return chang_jia;
    }

    public void setChang_jia(String chang_jia) {
        this.chang_jia = chang_jia;
    }

    public Timestamp getGengxin_shijian() {
        return gengxin_shijian;
    }

    public void setGengxin_shijian(Timestamp gengxin_shijian) {
        this.gengxin_shijian = gengxin_shijian;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "pos_id=" + pos_id +
                ", pos_bianhao='" + pos_bianhao + '\'' +
                ", louyu_mingcheng='" + louyu_mingcheng + '\'' +
                ", anzhuang_weizhi='" + anzhuang_weizhi + '\'' +
                ", weizhi_leixing='" + weizhi_leixing + '\'' +
                ", wei_zhi='" + wei_zhi + '\'' +
                ", qu_ju='" + qu_ju + '\'' +
                ", pon_biaoshi='" + pon_biaoshi + '\'' +
                ", biao_qian='" + biao_qian + '\'' +
                ", fenxian_he='" + fenxian_he + '\'' +
                ", olt_mingcheng='" + olt_mingcheng + '\'' +
                ", cao_lu=" + cao_lu +
                ", pon_duankou_hao=" + pon_duankou_hao +
                ", olt_ip_dizhi='" + olt_ip_dizhi + '\'' +
                ", olt_leixing='" + olt_leixing + '\'' +
                ", chang_jia='" + chang_jia + '\'' +
                ", gengxin_shijian=" + gengxin_shijian +
                '}';
    }
}
