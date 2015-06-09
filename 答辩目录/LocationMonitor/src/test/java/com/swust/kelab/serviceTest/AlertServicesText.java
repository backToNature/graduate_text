package com.swust.kelab.serviceTest;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swust.kelab.domain.Alert;
import com.swust.kelab.domain.Location;
import com.swust.kelab.domain.LocationRaw;
import com.swust.kelab.service.AlertService;

public class AlertServicesText {
	private AlertService alertService;
	
    @Before
    public void init() {
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext(
                        new String[]{"classpath:spring/applicationContext.xml",
                                     "classpath:spring/dao.xml",
                                     "classpath:spring/service.xml"});
        alertService = ctx.getBean(AlertService.class);
    }
	
	@Test
	public void createAlert() throws Exception{
		LocationRaw locationRaw = new LocationRaw();
		List<Alert>list;
		Date date = new Date();
		locationRaw.setImei("6IMEI");
		locationRaw.setImsi("6IMSI");
		locationRaw.setPosTime(date);
		locationRaw.setLongitude(String.valueOf(20));
		locationRaw.setLatitude(String.valueOf(20));
		locationRaw.setLongitudeBaidu(String.valueOf(30));
		locationRaw.setLatitudeBaidu(String.valueOf(30));
		locationRaw.setLocType(666);
		locationRaw.setTransferFlag(555);
		locationRaw.setAreaID(444);
		locationRaw.setSrcIP(String.valueOf(112));
		locationRaw.setDstIP(String.valueOf(911));
		locationRaw.setPhoneType(String.valueOf("iphone"));
		locationRaw.setAppType("Mobile phone taobao");
		list = alertService.createAlert(locationRaw);
		System.out.println(list.size());
		for(int i =0;i<list.size();i++){
			System.out.println(list.get(i).getImei());
			System.out.println(list.get(i).getImsi());
			System.out.println(list.get(i).getLongitudeBaidu());
			System.out.println(list.get(i).getLatitudeBaidu());
			System.out.println(list.get(i).getLocationID());
		}
	}
}
