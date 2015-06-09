package com.swust.kelab.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swust.kelab.domain.Alert;
import com.swust.kelab.domain.AlertConfig;
import com.swust.kelab.domain.AlertData;
import com.swust.kelab.domain.IMEI;
import com.swust.kelab.domain.IMSI;
import com.swust.kelab.domain.Location;
import com.swust.kelab.domain.LocationRaw;
import com.swust.kelab.domain.ObjectInfo;
import com.swust.kelab.repos.AlertDAO;
import com.swust.kelab.service.engine.JobLauncherDetails;

@Service("alertService")
public class AlertService {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private AlertDAO alertDAO;

	public AlertData selectAlertData(String strTime) throws Exception {
		AlertData alertData = new AlertData();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 日期格式
		Calendar calendar = Calendar.getInstance();
		String[] str = new String[2];
		Date[] da = new Date[2];
		Date date = new Date();
		int numDay = 0;
		int flag = 0;// 默认7天时间
		if (strTime == null || strTime == "") {
			numDay = 7;
		} else {
			flag = 1;
			str = strTime.split("-");
			for (int i = 0; i < 2; i++) {
				try {
					da[i] = sdf.parse(str[i]);

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(da[0]);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(da[1]);
			int flag2 = -2;// flag2 =-1/0/+1
			do {
				calendar1.add(Calendar.DAY_OF_YEAR, +1);
				flag2 = calendar1.getTime().compareTo(calendar2.getTime());
				numDay++;
			} while (flag2 <= 0);
		}
		String[] stime = new String[numDay];
		Integer[] series = new Integer[numDay];
		Integer[] series_resolved = new Integer[numDay];
		Integer[] series_n_resolve = new Integer[numDay];
		Integer[] locationNum = new Integer[numDay];
		if (flag == 0) {
			try {
				date = sdf.parse(sdf.format(new Date()));// 获得系统当前时间
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (flag == 1) {
			date = da[1];
		}
		calendar.setTime(date);
		for (int i = (numDay - 1); i >= 0; i--) {
			stime[i] = sdf.format(calendar.getTime());
			series[i] = alertDAO.selectSeries(calendar.getTime());
			series_resolved[i] = alertDAO.selectSeries_resolved(calendar
					.getTime());
			series_n_resolve[i] = alertDAO.selectSeries_n_resolve(calendar
					.getTime());
			locationNum[i] = alertDAO.selectLocationNum(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		alertData.setStrTime(stime);
		alertData.setSeries(series);
		alertData.setSeries_resolved(series_resolved);
		alertData.setSeries_n_resolve(series_n_resolve);
		alertData.setLocationNum(locationNum);
		return alertData;
	}

	/**
	 * 
	 * @description 保存告警
	 * @author libo
	 * @date 2015年4月18日 下午6:42:34
	 *
	 * @param alert
	 * @return
	 * @throws Exception
	 */
	public int insertAlert(ArrayList<Alert> alerts) throws Exception {
		int count = 0;
		for (Alert alert : alerts) {
			log.debug("insert Alert for location#" + alert.getLocationID());
			alertDAO.add(alert);
			count++;
		}
		return count;
	}

	public int insertObjectInfo(ObjectInfo objectInfo) throws Exception {
		return alertDAO.addObjectInfo(objectInfo);
	}

	/**
	 * 
	 * @description 导入采集端的原始位置信息到分析端的 location, imsi, imei, objectinfor四张表中
	 *              查询locationConfig表，确定是否产生告警，返回告警对象
	 * @author chenj
	 * @date 2015年4月19日 下午4:06:42
	 *
	 * @param locationRaw
	 *            来自数据采集端的位置数据
	 * @return
	 */

	public ArrayList<Alert> createAlert(LocationRaw locationRaw)
			throws Exception {

		Location item = new Location();
		IMEI imei = new IMEI();
		IMSI imsi = new IMSI();
		imei.setImei(locationRaw.getImei());
		imsi.setImsi(locationRaw.getImsi());

		int imeiID = alertDAO.getIMEIID(imei);
		if (imeiID == -1) {
			imeiID = alertDAO.addOneIMEI(imei);
			log.debug("-----------------" + "新增一条IMEI记录" + "-----------------");
			log.debug("-----------------" + "imeiID:" + imeiID
					+ "-----------------");
		}

		int imsiID = alertDAO.getIMSIID(imsi);
		if (imsiID == -1) {
			alertDAO.addOneIMSI(imsi);
			imsiID = alertDAO.getIMSIID(imsi);
			log.debug("-----------------" + "新增一条IMSI记录" + "-----------------");
			log.debug("-----------------" + "imsiID:" + imsiID
					+ "-----------------");
		}
		ObjectInfo objectInfo = new ObjectInfo();
		objectInfo.setIMEI_IMEIID(imeiID);
		objectInfo.setIMSI_IMSIID(imsiID);
		objectInfo.setObjectIMEI(locationRaw.getImei());
		objectInfo.setObjectIMSI(locationRaw.getImsi());
		int flagObjectInfo = alertDAO.selectObjectInfo(objectInfo);
		if (flagObjectInfo == -1) {
			alertDAO.addObjectInfo(objectInfo);
		}
		item.setImeiID(imeiID);
		item.setImsiID(imsiID);
		item.setPosTime(locationRaw.getPosTime());
		item.setLongitude(locationRaw.getLongitude());
		item.setLatitude(locationRaw.getLatitude());
		item.setLongitudeBaidu(locationRaw.getLongitudeBaidu());
		item.setLatitudeBaidu(locationRaw.getLatitudeBaidu());
		item.setLocType(locationRaw.getLocType());
		item.setTransferFlag(locationRaw.getTransferFlag());
		item.setAreaID(locationRaw.getAreaID());
		item.setSrcIP(locationRaw.getSrcIP());
		item.setDstIP(locationRaw.getDstIP());
		item.setPhoneType(locationRaw.getPhoneType());
		item.setAppType(locationRaw.getAppType());
		int locationID = alertDAO.addOneLocation(item);
		log.debug("-----------------" + "新增一条Location记录" + "-----------------");
		log.debug("-----------------" + locationID + "-----------------");
		Double longitudeBaidu = Double.valueOf(locationRaw.getLongitudeBaidu());
		log.debug("longitudeBaidu :" + longitudeBaidu);
		Double latitudeBaidu = Double.valueOf(locationRaw.getLatitudeBaidu());
		log.debug("latitudeBaidu :" + latitudeBaidu);

		// 获取告警设置（默认检查时间条件）
		List<AlertConfig> listAlertConfig = alertDAO
				.getListAlertConfig(locationRaw);
		log.debug("-----------------" + "listAlertConfig.size:"
				+ listAlertConfig.size() + "-----------------");
		// 去掉不满足条件的
		for (int i = 0; i < listAlertConfig.size(); i++) {
			log.debug("AlertConfigID:"
					+ listAlertConfig.get(i).getAlertConfigID());
		}
		Iterator<AlertConfig> iterator = listAlertConfig.iterator();
		while (iterator.hasNext()) {
			AlertConfig alertConfig = (AlertConfig) iterator.next();
			int flag = 0;// 不满足条件时flag=0
			// 检查告警设置中的范围
			if (!alertConfig.getLongitudeA().trim().isEmpty()) {
				Double longitudeA = Double.valueOf(alertConfig.getLongitudeA());
				Double longitudeB = Double.valueOf(alertConfig.getLongitudeB());
				Double latitudeA = Double.valueOf(alertConfig.getLatitudeA());
				Double latitudeB = Double.valueOf(alertConfig.getLatitudeB());
				if ((longitudeBaidu >= longitudeA && longitudeBaidu <= longitudeB)
						&& (latitudeBaidu >= latitudeA && latitudeBaidu <= latitudeB)) {
					flag = 1;
				}
			}
			// 检查告警设置中的IMEI和IMSI
			if (!alertConfig.getImei().isEmpty()) {
				if (!alertConfig.getImsi().isEmpty())
					if (locationRaw.getImei() == alertConfig.getImei()
							&& locationRaw.getImsi() == alertConfig.getImsi()) {
						flag = 1;
					} else {
						if (locationRaw.getImei() == alertConfig.getImei()) {
							flag = 1;
						}
					}
			} else {
				if (!alertConfig.getImsi().isEmpty()) {
					if (locationRaw.getImsi() == alertConfig.getImsi()) {
						flag = 1;
					}
				} else {
					flag = 1;
				}
			}

			if (flag == 0) {
				iterator.remove();
			}
		}
		for (int i = 0; i < listAlertConfig.size(); i++) {
			log.debug("AlertConfigID:"
					+ listAlertConfig.get(i).getAlertConfigID());
		}
		ArrayList<Alert> listAlert = new ArrayList<Alert>();
		for (int i = 0; i < listAlertConfig.size(); i++) {
			Alert alert = new Alert();
			alert.setAlertConfigID(listAlertConfig.get(i).getAlertConfigID());
			alert.setLocationID(locationID);
			alert.setImei(imei.getImei());
			alert.setImsi(imsi.getImsi());
			alert.setPosTime(item.getPosTime());
			alert.setLongitudeBaidu(item.getLongitudeBaidu());
			alert.setLatitudeBaidu(item.getLatitudeBaidu());
			alert.setAreaID(item.getAreaID());
			alert.setPhoneType(item.getPhoneType());
			alert.setAppType(item.getAppType());
			alert.setAlertState(0);
			alert.setPushState(listAlertConfig.get(i).getPushFlag());
			listAlert.add(alert);
		}

		insertAlert(listAlert);
		return listAlert;

	}
}
