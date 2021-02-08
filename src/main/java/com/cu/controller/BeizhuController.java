package com.cu.controller;

import com.cu.entity.Beizhu;
import com.cu.entity.Pos;
import com.cu.service.BeizhuService;
import com.cu.service.PosService;
import com.cu.util.CryptoUtil;
import com.ultrapower.casp.client.LoginUtil;
import com.ultrapower.casp.common.code.ResultCode;
import com.ultrapower.casp.common.datatran.data.ticket.TransferTicket;
import com.ultrapower.casp.common.datatran.data.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * POS检索&备注&审核的控制器
 */
@Controller
public class BeizhuController {

    // 自动装载POS检索&备注&审核的服务
    @Autowired
    BeizhuService beizhuService;


    // 提交POS备注
    @RequestMapping(value="/api/commitBeizhu", method = RequestMethod.POST)
    @ResponseBody
    public String commitBeizhu(HttpServletRequest request){
        String beizhu_id = UUID.randomUUID().toString();
        Pos pos_beizhu = new Pos();
        pos_beizhu.setPos_id(Integer.parseInt(request.getParameter("pos_id")));
        pos_beizhu.setPos_bianhao(request.getParameter("pos_bianhao"));
        pos_beizhu.setLouyu_mingcheng(request.getParameter("louyu_mingcheng"));
        pos_beizhu.setWeizhi_leixing(request.getParameter("weizhi_leixing"));
        pos_beizhu.setWei_zhi(request.getParameter("wei_zhi"));
        pos_beizhu.setAnzhuang_weizhi(request.getParameter("anzhuang_weizhi"));
        pos_beizhu.setQu_ju(request.getParameter("qu_ju"));
        pos_beizhu.setPon_biaoshi(request.getParameter("pon_biaoshi"));
        pos_beizhu.setBiao_qian(request.getParameter("biao_qian"));
        pos_beizhu.setFenxian_he(request.getParameter("fenxian_he"));
        pos_beizhu.setOlt_mingcheng(request.getParameter("olt_mingcheng"));
        pos_beizhu.setCao_lu(Integer.parseInt(request.getParameter("cao_lu")));
        pos_beizhu.setPon_duankou_hao(Integer.parseInt(request.getParameter("pon_duankou_hao")));
        pos_beizhu.setOlt_ip_dizhi(request.getParameter("olt_ip_dizhi"));
        pos_beizhu.setOlt_leixing(request.getParameter("olt_leixing"));
        pos_beizhu.setChang_jia(request.getParameter("chang_jia"));
        String bei_zhu = request.getParameter("bei_zhu");
        String caozuo_yuan = request.getParameter("caozuo_yuan");
        if (caozuo_yuan.length() > 16){
            caozuo_yuan = CryptoUtil.desEncrypt(caozuo_yuan);
        }
        String caozuo_yuan_dianhua = request.getParameter("caozuo_yuan_dianhua");
        if (caozuo_yuan_dianhua.length() > 16){
            caozuo_yuan_dianhua = CryptoUtil.desEncrypt(caozuo_yuan_dianhua);
        }
        return beizhuService.commitBeizhu(pos_beizhu, bei_zhu, caozuo_yuan, caozuo_yuan_dianhua);
    }


    // 审核POS备注
    @RequestMapping(value="/api/checkBeizhu", method = RequestMethod.POST)
    @ResponseBody
    public String checkBeizhu(HttpServletRequest request){
        String beizhu_id = request.getParameter("beizhu_id");
        String shenhe_yuan = request.getParameter("shenhe_yuan");
        return beizhuService.checkBeizhu(beizhu_id, shenhe_yuan);
    }

    // 废弃POS备注
    @RequestMapping(value="/api/abandonBeizhu", method = RequestMethod.POST)
    @ResponseBody
    public String abandonBeizhu(HttpServletRequest request){
        String beizhu_id = request.getParameter("beizhu_id");
        String shenhe_yuan = request.getParameter("shenhe_yuan");
        return beizhuService.abandonBeizhu(beizhu_id, shenhe_yuan);
    }

    // 获取未审核POS备注列表
    @RequestMapping(value="/api/getBeizhuList", method = RequestMethod.GET)
    @ResponseBody
    public List<Beizhu> getBeizhuList(){
        return beizhuService.getBeizhuList(0);
    }

    // 获取已审核POS备注列表
    @RequestMapping(value="/api/getBeizhuHistory", method = RequestMethod.GET)
    @ResponseBody
    public List<Beizhu> getBeizhuHistory(){
        return beizhuService.getBeizhuList(1);
    }


    // 页面:POS备注列表
    @RequestMapping(value = "/pos_beizhu_list", method = RequestMethod.GET)
    public String posBeizhuList(Model model){
        model.addAttribute("uid", "user");
        model.addAttribute("uname","管理员");
        return "pos_beizhu_list";
    }
}
