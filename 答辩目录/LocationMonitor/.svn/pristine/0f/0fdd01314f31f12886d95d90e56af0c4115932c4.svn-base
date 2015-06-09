package com.swust.kelab.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.domain.Account;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.service.AccountService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/leader")
public class AccountController {
    @Resource
    private AccountService accountService;
    
    /**
     * 鍒嗛〉鏌ヨ鎰忚棰嗚
     * @return
     */
    @RequestMapping(value = "/viewLeaders", method = RequestMethod.GET)
    public JsonAndView viewLeaders(CommonQuery query) {
        JsonAndView jv = new JsonAndView();
        QueryData result = accountService.viewLeaders(query);
        if (result == null) {
            jv.setRet(false);
            jv.setErrcode(1);
            jv.setErrmsg("error");
        } else {
            jv.addData("totalPage", result.getTotalPage());
            jv.addData("totalCount", result.getTotalCount());
            jv.addData("pageData", result.getPageData());
        }
        return jv;
    }
    
    
    
}
