package com.cu.service;

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
public class PosService {

    // 自动装载POS检索&备注&审核的数据接口
    @Autowired
    PosDao posDao;

    /**
     * 根据关键字、区局、上联标识获取POS列表
     * @param keyword 关键字
     * @param qu_ju 区局
     * @param up_link 上联标识
     *
     * @return
     * @throws Exception
     */
    public List<Pos> getPosList(String keyword, String qu_ju, String up_link) throws Exception {
        return posDao.getPosList(keyword,  qu_ju,  up_link);
    }

}
