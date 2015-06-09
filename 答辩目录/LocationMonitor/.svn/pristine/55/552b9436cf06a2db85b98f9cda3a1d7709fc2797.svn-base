package com.swust.kelab.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.model.SMSModel;
import com.swust.kelab.service.SendmsgService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/SMS")
public class SMSController{
	@Autowired
	private  SendmsgService sendmsgservice;
@RequestMapping(value="/SendPointInfo",method = RequestMethod.GET)
public JsonAndView SendpointInfo(SMSModel SMSModel){
	JsonAndView jv = new JsonAndView();
	try{
sendmsgservice.Send(SMSModel.getPhone(),SMSModel.getImsiId(),SMSModel.getImeiId(),SMSModel.getName(),SMSModel.getLongitude(),SMSModel.getLatitude());	    
	}catch(Exception e){
		e.printStackTrace();
	}
	jv.setRet(true);
	return jv;
}

@RequestMapping(value = "/SendAlarmPointInfo",method = RequestMethod.GET)
public JsonAndView SendAlarmPoint(SMSModel sms){
	JsonAndView jsonv  = new JsonAndView();
	try{
	sendmsgservice.Send(sms.getPhone(),sms.getImsiId(),sms.getImeiId(),sms.getName(),sms.getLongitude(),sms.getLatitude());
	}catch(Exception ee){
		ee.printStackTrace();
	}
	jsonv.setRet(true);
	return jsonv;
}
}
