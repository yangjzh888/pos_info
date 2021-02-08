package com.cu.entity;

import java.sql.Timestamp;

/**
 * 实体类:备注
 */
public class Beizhu {
    // 备注ID
    private String beizhu_id;

    // POS对象
    private Pos pos;

//    // POS_ID
//    private int pos_id;
//    // POS编号
//    private String pos_bianhao;
//    // 楼宇名称
//    private String louyu_mingcheng;
//    // 安装位置
//    private String anzhuang_weizhi;
//    // 区局
//    private String qu_ju;
//    // 上联标识
//    private String pon_biaoshi;
//    // 二维码编号
//    private String biao_qian;
//    // 分纤盒
//    private String fenxian_he;
//    // OLT名称
//    private String olt_mingcheng;
//    // 槽路
//    private int cao_lu;
//    // 端口号
//    private int pon_duankou_hao;
//    // OLT_IP地址
//    private String olt_ip_dizhi;
//    // OLT类型
//    private String olt_leixing;
//    // 厂家
//    private String chang_jia;
//    // 更新时间
//    private Timestamp gengxin_shijian;

    // 备注(外线)
    private String bei_zhu;
    // 备注(局内)
    private String bei_zhu1;
    // 操作员
    private String caozuo_yuan;
    // 操作员电话
    private String caozuo_yuan_dianhua;
    // 操作时间
    private Timestamp caozuo_shijian;
    // 审核员
    private String shenhe_yuan;
    // 审核时间
    private Timestamp shenhe_shijian;

    public String getBeizhu_id() {
        return beizhu_id;
    }

    public void setBeizhu_id(String beizhu_id) {
        this.beizhu_id = beizhu_id;
    }

//    public int getPos_id() {
//        return pos_id;
//    }
//
//    public void setPos_id(int pos_id) {
//        this.pos_id = pos_id;
//    }
//
//    public String getPos_bianhao() {
//        return pos_bianhao;
//    }
//
//    public void setPos_bianhao(String pos_bianhao) {
//        this.pos_bianhao = pos_bianhao;
//    }
//
//    public String getLouyu_mingcheng() {
//        return louyu_mingcheng;
//    }
//
//    public void setLouyu_mingcheng(String louyu_mingcheng) {
//        this.louyu_mingcheng = louyu_mingcheng;
//    }
//
//    public String getAnzhuang_weizhi() {
//        return anzhuang_weizhi;
//    }
//
//    public void setAnzhuang_weizhi(String anzhuang_weizhi) {
//        this.anzhuang_weizhi = anzhuang_weizhi;
//    }
//
//    public String getQu_ju() {
//        return qu_ju;
//    }
//
//    public void setQu_ju(String qu_ju) {
//        this.qu_ju = qu_ju;
//    }
//
//    public String getPon_biaoshi() {
//        return pon_biaoshi;
//    }
//
//    public void setPon_biaoshi(String pon_biaoshi) {
//        this.pon_biaoshi = pon_biaoshi;
//    }
//
//    public String getBiao_qian() {
//        return biao_qian;
//    }
//
//    public void setBiao_qian(String biao_qian) {
//        this.biao_qian = biao_qian;
//    }
//
//    public String getFenxian_he() {
//        return fenxian_he;
//    }
//
//    public void setFenxian_he(String fenxian_he) {
//        this.fenxian_he = fenxian_he;
//    }
//
//    public String getOlt_mingcheng() {
//        return olt_mingcheng;
//    }
//
//    public void setOlt_mingcheng(String olt_mingcheng) {
//        this.olt_mingcheng = olt_mingcheng;
//    }
//
//    public int getCao_lu() {
//        return cao_lu;
//    }
//
//    public void setCao_lu(int cao_lu) {
//        this.cao_lu = cao_lu;
//    }
//
//    public int getPon_duankou_hao() {
//        return pon_duankou_hao;
//    }
//
//    public void setPon_duankou_hao(int pon_duankou_hao) {
//        this.pon_duankou_hao = pon_duankou_hao;
//    }
//
//    public String getOlt_ip_dizhi() {
//        return olt_ip_dizhi;
//    }
//
//    public void setOlt_ip_dizhi(String olt_ip_dizhi) {
//        this.olt_ip_dizhi = olt_ip_dizhi;
//    }
//
//    public String getOlt_leixing() {
//        return olt_leixing;
//    }
//
//    public void setOlt_leixing(String olt_leixing) {
//        this.olt_leixing = olt_leixing;
//    }
//
//    public String getChang_jia() {
//        return chang_jia;
//    }
//
//    public void setChang_jia(String chang_jia) {
//        this.chang_jia = chang_jia;
//    }
//
//    public Timestamp getGengxin_shijian() {
//        return gengxin_shijian;
//    }
//
//    public void setGengxin_shijian(Timestamp gengxin_shijian) {
//        this.gengxin_shijian = gengxin_shijian;
//    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public String getBei_zhu() {
        return bei_zhu;
    }

    public void setBei_zhu(String bei_zhu) {
        this.bei_zhu = bei_zhu;
    }

    public String getBei_zhu1() {
        return bei_zhu1;
    }

    public void setBei_zhu1(String bei_zhu1) {
        this.bei_zhu1 = bei_zhu1;
    }

    public String getCaozuo_yuan() {
        return caozuo_yuan;
    }

    public void setCaozuo_yuan(String caozuo_yuan) {
        this.caozuo_yuan = caozuo_yuan;
    }

    public String getCaozuo_yuan_dianhua() {
        return caozuo_yuan_dianhua;
    }

    public void setCaozuo_yuan_dianhua(String caozuo_yuan_dianhua) {
        this.caozuo_yuan_dianhua = caozuo_yuan_dianhua;
    }

    public Timestamp getCaozuo_shijian() {
        return caozuo_shijian;
    }

    public void setCaozuo_shijian(Timestamp caozuo_shijian) {
        this.caozuo_shijian = caozuo_shijian;
    }

    public String getShenhe_yuan() {
        return shenhe_yuan;
    }

    public void setShenhe_yuan(String shenhe_yuan) {
        this.shenhe_yuan = shenhe_yuan;
    }

    public Timestamp getShenhe_shijian() {
        return shenhe_shijian;
    }

    public void setShenhe_shijian(Timestamp shenhe_shijian) {
        this.shenhe_shijian = shenhe_shijian;
    }


}
