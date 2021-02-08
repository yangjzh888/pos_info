package com.cu.service;

import com.cu.dao.BeizhuDao;
import com.cu.dao.PosDao;
import com.cu.entity.Beizhu;
import com.cu.entity.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * POS检索&备注&审核的服务
 */
@Service
public class BeizhuService {

    // 自动装载POS检索&备注&审核的数据接口
    @Autowired
    BeizhuDao beizhuDao;

    /**
     * 提交POS备注
     * @param pos 分光器对象
     * @param bei_zhu 备注
     * @param caozuo_yuan 操作员
     * @param caozuo_yuan_dianhua 操作员电话
     *
     * @return
     */
    public String commitBeizhu(Pos pos, String bei_zhu, String caozuo_yuan, String caozuo_yuan_dianhua){
        return beizhuDao.commitBeizhu(pos, bei_zhu, caozuo_yuan, caozuo_yuan_dianhua);
    }


    /**
     * 审核备注
     * @param beizhu_id 备注ID
     * @param shenhe_yuan 审核员
     * @return
     */
    public String checkBeizhu(String beizhu_id, String shenhe_yuan){
        return beizhuDao.checkBeizhu(beizhu_id, 1, shenhe_yuan);
    }

    /**
     * 废弃备注
     * @param beizhu_id 备注ID
     * @param shenhe_yuan 审核员
     * @return
     */
    public String abandonBeizhu(String beizhu_id, String shenhe_yuan){
        return beizhuDao.checkBeizhu(beizhu_id, 2, shenhe_yuan);
    }


    /**
     * 根据审核状态获取POS备注列表
     * @param shenhe_zhuangtai 审核状态(0:未审核; 1:已审核)
     *
     * @return
     */
    public List<Beizhu> getBeizhuList(int shenhe_zhuangtai) {
        return beizhuDao.getBeizhuList(shenhe_zhuangtai);
    }


}
