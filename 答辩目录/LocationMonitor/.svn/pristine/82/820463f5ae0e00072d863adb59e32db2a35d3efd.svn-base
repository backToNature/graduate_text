package com.swust.kelab.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.service.AccountService;

public class AccountServiceTest {
    private AccountService accountService;
    
    @Before
    public void init() {
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext(
                        new String[]{"classpath:spring/applicationContext.xml",
                                     "classpath:spring/dao.xml",
                                     "classpath:spring/service.xml"});
        accountService = ctx.getBean(AccountService.class);
    }
    
    @Test
    public void viewLeaders(){
        QueryData leaders=accountService.viewLeaders(new CommonQuery());
        if(leaders==null){
            System.out.println("wrong!");
        }
        System.out.println(leaders.getTotalCount());
        System.out.println(leaders.getPageData().size());
        
    }
    
   
}
