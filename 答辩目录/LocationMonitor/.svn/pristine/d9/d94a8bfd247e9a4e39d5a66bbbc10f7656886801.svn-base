package com.swust.kelab.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.service.AlertService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/selectAlert")

public class AlertController {
	@Autowired
	private AlertService alertService;
	@RequestMapping(value="/viewAlert",method=RequestMethod.GET)
	public JsonAndView view (String strTime){
		JsonAndView jav = new JsonAndView();
		try {
			jav.addData("date", alertService.selectAlertData(strTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jav.setRet(true);
		return jav;	
	}
}
