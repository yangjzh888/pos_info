package com.cu.controller;

import com.cu.entity.Beizhu;
import com.cu.entity.Pos;
import com.cu.service.PosService;
import com.ultrapower.casp.common.code.ResultCode;
import com.ultrapower.casp.common.datatran.data.ticket.TransferTicket;
import com.ultrapower.casp.common.datatran.data.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import com.ultrapower.casp.client.LoginUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * POS检索&备注&审核的控制器
 */
@Controller
public class PosController {

    // 自动装载POS检索&备注&审核的服务
    @Autowired
    PosService posService;

    // 根据关键字、区局、上联标识获取POS列表
    @RequestMapping(value = "/api/getPosList", method = RequestMethod.GET)
    @ResponseBody
    public List<Pos> getPosList(String keyword, String qu_ju, String up_link) throws Exception {
        // 关键字输入为空
        if(keyword.equals("")) {
            // 默认查询关键字为"1"、上联标识为10GEPON的POS列表
            return posService.getPosList("1", qu_ju, up_link);
        }
        return posService.getPosList(keyword, qu_ju, up_link);
    }


    @RequestMapping(value = "/")
    public String index(){
        //initTokenConfig();
        LoginUtil.getInstance().init("FGQSH", "YW6wEsWBSyU=");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // System.out.println(LoginUtil.getInstance().isEnable());
        if (LoginUtil.getInstance().isEnable()) {
            if (LoginUtil.getInstance().checkTicket(request)) {
                String strTic = LoginUtil.getInstance().getTicket(request);
                System.out.println(strTic);
                TransferTicket ticket = LoginUtil.getInstance().analysTicket(strTic);
                if (ticket != null && ticket.getRetCode() != null && ticket.getRetCode().equals(ResultCode.RESULT_OK)) {
                    UserInfo userInfo = LoginUtil.getInstance().qryUserByTicket(ticket);
//                    System.out.println(userInfo.getAccountID());
//                    System.out.println(userInfo.getName());
//                    System.out.println(userInfo.getEmail());
//                    System.out.println(userInfo.getEmpNo());
//                    System.out.println(userInfo.getGroupName());
//                    System.out.println(userInfo.getIdCardNum());
//                    System.out.println(userInfo.getMenuDatas());
//                    System.out.println(userInfo.getMobile());
//                    System.out.println(userInfo.getOrgDatas());
//                    System.out.println(userInfo.getPhone());

                    if (!userInfo.getRetCode().equals(ResultCode.RESULT_OK)) {
                        //根据错误码生成提示信息；
                        return "error";
                    } else {
                        //应用资源根据帐号信息做登录后业务处理；
                        return "pos_beizhu_list";
                    }
                } else {
                    //根据错误码生成提示信息；
                    return "error";
                }
            }
        } else {
            //使用应用资源本地认证；
            return "pos_beizhu_list";
        }
        return "error";
    }

}
