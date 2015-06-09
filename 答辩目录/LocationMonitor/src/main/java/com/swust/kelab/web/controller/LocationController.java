package com.swust.kelab.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.domain.Location;
import com.swust.kelab.domain.User;
import com.swust.kelab.service.LocationService;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/location")
public class LocationController {
@Autowired
private LocationService locationService;

  @RequestMapping(value="/add",method=RequestMethod.GET)
  public JsonAndView addOne(Location location){
		 JsonAndView jv = new JsonAndView();
		 try {
			locationService.add(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 jv.setRet(true);
		 return jv;
	}
  @RequestMapping(value="/delete",method=RequestMethod.GET)
  public JsonAndView deleteOne(Location location){
		 JsonAndView jv = new JsonAndView();
		 try {
			locationService.delete(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 jv.setRet(true);
		 return jv;
	}
  @RequestMapping(value="/update",method=RequestMethod.GET)
  public JsonAndView updateOne(Location location){
		 JsonAndView jv = new JsonAndView();
		 locationService.updateOne(location);
		 jv.setRet(true);
		 return jv;
  }
  @RequestMapping(value="/selectIMEI",method=RequestMethod.GET)
  public JsonAndView selectIMEI(String imei){
	  JsonAndView jv = new JsonAndView();
	  locationService.selectIMEI(imei);
	  jv.addData("location",  locationService.selectIMEI(imei));
	  jv.setRet(true);
	return jv;
  }
  @RequestMapping(value="/selectIMSI",method=RequestMethod.GET)
  public JsonAndView selectIMSI(String imsi){
	  JsonAndView jv = new JsonAndView();
	  locationService.selectIMSI(imsi);
	  jv.addData("location",  locationService.selectIMSI(imsi));
	  jv.setRet(true);
	return jv;
  }
 
  

}
