package com.cu.macro;

/**
 * 常量定义
 */
public class Constants {

    // SQL语句:网络资源数据库POS全量信息
    final public static String sqlPosFull = "SELECT " +
            "   o.id POS_ID," +
            "   prmtools.f_querybyid('bu_men',o.qu_ju,'bumen_mingcheng') QU_JU," +
            "   o.shebei_bianhao POS_BIANHAO," +
            "   o.anzhuang_weizhi ANZHUANG_WEIZHI," +
            "   prmtools.f_querybyid('shebei_dalei',o.weizhi_leixing,'shebei_dalei') WEIZHI_LEIXING," +
            "   prmtools.f_querybyid(sd.shujuku_biao, o.weizhi_id, sd.bianma_ziduan_ming) WEI_ZHI," +
            "   m.louyu_mingcheng LOUYU_MINGCHENG," +
            "   CASE " +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi=1 THEN '10GEPON'" +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi<>1 THEN 'EPON'" +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi IS NULL THEN 'EPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi=1 THEN 'GPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi<>1 THEN 'GPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi IS NULL THEN 'GPON'" +
            "   END PON_BIAOSHI," +
            "   bq.bian_hao BIAO_QIAN," +
            "   g.bian_hao FENXIAN_HE," +
            "   wy.olt_wangyuan_mingcheng OLT_MINGCHENG," +
            "   dk.cao_lu CAO_LU," +
            "   dk.duankou_hao PON_DUANKOU_HAO," +
            "   wy.ip_dizhi OLT_IP_DIZHI," +
            "   prmtools.f_querybydictid('olt_wangyuan',wy.olt_leixing,'olt_leixing') OLT_LEIXING," +
            "   prmtools.f_querybydictid('olt_wangyuan',wy.chang_jia,'chang_jia') CHANG_JIA " +
            "FROM " +
            "   odn_shebei o," +
            "   pon_pos_duizhao dz," +
            "   olt_duankou dk," +
            "   olt_banka bk," +
            "   banka_peizhi pz," +
            "   olt_wangyuan wy," +
            "   ji_fang jf," +
            "   jianzhu_wu jz," +
            "   biao_qian bq," +
            "   guang_fenqian_he g," +
            "   shebei_dalei sd," +
            "   (SELECT DISTINCT " +
            "       mx.louyu_bianhao,mx.louyu_mingcheng " +
            "    FROM jxgl.louyu_mingxi mx) m " +
            "WHERE o.qu_ju=20009 " +
            "   AND o.id = dz.duiying_pos_id(+) " +
            "   AND dz.pon_duankou_id = dk.id(+) " +
            "   AND dk.banka_id = bk.id(+) " +
            "   AND bk.banka_leixing = pz.zidian_zhi(+) " +
            "   AND bk.olt_id = wy.id(+) " +
            "   AND o.weizhi_id = jf.id(+) " +
            "   AND jf.jianzhu_wu_id = jz.id(+) " +
            "   AND jz.louyu_xuhao = m.louyu_bianhao(+) " +
            "   AND o.biaoqian_id = bq.id(+) " +
            "   AND o.id = g.duiying_pos_id(+)" +
            "   AND o.weizhi_leixing = sd.id(+) " +
            "ORDER BY o.id ASC";

    // SQL语句:网络资源数据库POS增量信息
    final public static String getSqlPosIncrement = "SELECT " +
            "   o.id POS_ID," +
            "   prmtools.f_querybyid('bu_men',o.qu_ju,'bumen_mingcheng') QU_JU," +
            "   o.shebei_bianhao POS_BIANHAO," +
            "   o.anzhuang_weizhi ANZHUANG_WEIZHI," +
            "   prmtools.f_querybyid('shebei_dalei',o.weizhi_leixing,'shebei_dalei') WEIZHI_LEIXING," +
            "   prmtools.f_querybyid(sd.shujuku_biao, o.weizhi_id, sd.bianma_ziduan_ming) WEI_ZHI," +
            "   m.louyu_mingcheng LOUYU_MINGCHENG," +
            "   CASE " +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi=1 THEN '10GEPON'" +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi<>1 THEN 'EPON'" +
            "   WHEN pz.pon_biaoshi = 0 AND wy.shanglian_biaoshi IS NULL THEN 'EPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi=1 THEN 'GPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi<>1 THEN 'GPON'" +
            "   WHEN pz.pon_biaoshi = 1 AND wy.shanglian_biaoshi IS NULL THEN 'GPON'" +
            "   END PON_BIAOSHI," +
            "   bq.bian_hao BIAO_QIAN," +
            "   g.bian_hao FENXIAN_HE," +
            "   wy.olt_wangyuan_mingcheng OLT_MINGCHENG," +
            "   dk.cao_lu CAO_LU," +
            "   dk.duankou_hao PON_DUANKOU_HAO," +
            "   wy.ip_dizhi OLT_IP_DIZHI," +
            "   prmtools.f_querybydictid('olt_wangyuan',wy.olt_leixing,'olt_leixing') OLT_LEIXING," +
            "   prmtools.f_querybydictid('olt_wangyuan',wy.chang_jia,'chang_jia') CHANG_JIA " +
            "FROM " +
            "   odn_shebei o," +
            "   pon_pos_duizhao dz," +
            "   olt_duankou dk," +
            "   olt_banka bk," +
            "   banka_peizhi pz," +
            "   olt_wangyuan wy," +
            "   ji_fang jf," +
            "   jianzhu_wu jz," +
            "   biao_qian bq," +
            "   guang_fenqian_he g," +
            "   shebei_dalei sd," +
            "   (SELECT DISTINCT " +
            "       mx.louyu_bianhao,mx.louyu_mingcheng " +
            "    FROM jxgl.louyu_mingxi mx " +
            "    WHERE to_char(mx.caozuo_shijian,'yyyy-MM-dd')>=?) m " +
            "WHERE o.qu_ju=20009 " +
            "   AND o.id = dz.duiying_pos_id(+) " +
            "   AND dz.pon_duankou_id = dk.id(+) " +
            "   AND dk.banka_id = bk.id(+) " +
            "   AND bk.banka_leixing = pz.zidian_zhi(+) " +
            "   AND bk.olt_id = wy.id(+) " +
            "   AND o.weizhi_id = jf.id(+) " +
            "   AND jf.jianzhu_wu_id = jz.id(+) " +
            "   AND jz.louyu_xuhao = m.louyu_bianhao(+) " +
            "   AND o.biaoqian_id = bq.id(+) " +
            "   AND o.id = g.duiying_pos_id(+)" +
            "   AND o.weizhi_leixing = sd.id(+) " +
            "   AND (to_char(o.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(dz.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(dk.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(bk.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(pz.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(wy.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(jf.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(jz.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(bq.caozuo_shijian,'yyyy-MM-dd')>=? " +
            "        OR to_char(g.caozuo_shijian,'yyyy-MM-dd')>=?) " +
            "ORDER BY o.id";

}
