package com.swust.kelab.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.swust.kelab.domain.LocationSimple;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.ObjModel;
import com.swust.kelab.model.PageData;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.repos.LocationQueryDAO;
import com.swust.kelab.repos.bean.ListQuery;
import com.swust.kelab.service.engine.JobLauncherDetails;
import com.swust.kelab.util.FormatUtil;
import com.swust.kelab.util.PageHandle;

@Service("locationQueryService")
public class LocationQueryService {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private LocationQueryDAO locationQueryDAO;
	private List<ObjModel> objectList;
	private ObjModel nowObject;

	/**
	 * @description分页查询
	 * @author CHENJIAO
	 * @param objm
	 * @param query
	 * @param pageNumber
	 * @return
	 */
	public QueryData queryBypage(ObjModel objm, CommonQuery query,
			Integer pageNumber, Integer locCount) {

		log.debug("---locCount--- " + locCount);
		Date time1 = new Date();
		// 取出接收到的参数
		String imei = objm.getImei();
		String imsi = objm.getImsi();
		String phone = objm.getPhone();
		String longitudeA = objm.getLongitudeA();
		String latitudeA = objm.getLatitudeA();
		String longitudeB = objm.getLongitudeB();
		String latitudeB = objm.getLatitudeB();

		log.debug("objmIMEI:" + imei);

		QueryData result = new QueryData();
		int totalPage = 0;
		ListQuery queryMap = query.format();
		queryMap.fill("imei", imei);
		queryMap.fill("imsi", imsi);
		queryMap.fill("phone", phone);

		// 查询符合条件的对象数量
		int allCount = locationQueryDAO.countObjNum(queryMap);

		log.debug("显示页码:" + pageNumber);

		if (query.getPageArray() == null && pageNumber != null) {
			query.setPageArray(new int[] { pageNumber });
		} else {
			query.setPageArray(new int[] { 1 });
		}

		// 查询的起始位置和长度
		int startIndex = 0;
		queryMap.fill("startIndex", startIndex);
		queryMap.fill("maxCount", allCount);

		// 查询符合条件的对象
		objectList = locationQueryDAO.queryObjects(queryMap);

		log.debug("objectsize:" + objectList.size());

		// 对每个对象进行地理位置查询操作
		Iterator objIterator = objectList.iterator();
		while (objIterator.hasNext()) {
			ObjModel object = (ObjModel) objIterator.next();
			log.debug("imei:" + object.getImei() + "\nimsi:" + object.getImsi());
			if (longitudeA != null && longitudeA != "" && longitudeB != null
					&& longitudeB != "" && latitudeA != null && latitudeA != ""
					&& latitudeB != null && latitudeB != "") {
				object.setLongitudeA(longitudeA);
				object.setLongitudeB(longitudeB);
				object.setLatitudeA(latitudeA);
				object.setLatitudeB(latitudeB);
			}

			// 为每一个对象添加时间限制
			String time = new String();
			time = objm.getTime();
			if (time != null && time != "") {
				String dateStr[] = time.split(" - ");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm");
				try {
					object.setDateStart(sdf.parse(dateStr[0]));
					object.setDateEnd(sdf.parse(dateStr[1]));
				} catch (ParseException e) {
					e.printStackTrace();
					log.debug("时间转化异常");
					return null;
				}
			}
			log.debug("-----位置查询-----");
			// 查询该对象对应的地理位置的第一页
			CommonQuery locQuery = new CommonQuery();
			locQuery.setRecordPerPage(locCount);
			int locPageNum = 1;
			QueryData locData = new QueryData();
			locData = this.queryLocByPage(locQuery, locPageNum, object);
			object.setLocData(locData);
			List<LocationSimple> items = null;
			if (locData != null) {
				List<PageData> pageDataList = locData.getPageData();
				log.debug("pageDataListSize:" + pageDataList.size());
				if (pageDataList.size() != 0) {
					PageData pageData = pageDataList.get(0);
					List<LocationSimple> locList = (List<LocationSimple>) pageData
							.getData();
					items = locList;
					time = FormatUtil.formatDate(locList.get(0).getTime())
							+ " - "
							+ FormatUtil.formatDate(locList.get(
									locList.size() - 1).getTime());
					object.setTime(time);
				} else {
					objIterator.remove();
					allCount--;
				}
			}
			object.setLocData(null);
			object.setItems(items);
		}
		// 设置新的总数量
		result.setTotalCount(allCount);
		// 计算总页数
		totalPage = QueryData.computeTotalPage(allCount,
				query.getRecordPerPage());
		result.setTotalPage(totalPage);
		// 为每个对象编号并移除不需要返回的对象
		int objId = 0;
		Iterator objIter = objectList.iterator();
		while (objIter.hasNext()) {
			ObjModel object = (ObjModel) objIter.next();
			object.setObjId(++objId);
			if (objId <= (pageNumber - 1) * query.getRecordPerPage()
					|| objId > pageNumber * query.getRecordPerPage()) {
				objIter.remove();
			}
		}
		log.debug("objectSize:" + objectList.size());
		Date time2 = new Date();
		long time = time2.getTime() - time1.getTime();
		log.debug("service运行时间：" + time);
		List<PageData> pageDataList = Lists.newArrayList();
		return PageHandle.initialQueryData(result, pageDataList, query,
				objectList, totalPage);
	}
	
	
	/**
	 * @description 分页查询对象（有操作判空）
	 * @param objm
	 * @param query
	 * @param pageNumber
	 * @param locCount
	 * @return
	 */
	public QueryData queryObjBypage(ObjModel objm, CommonQuery query,
			Integer pageNumber, Integer locCount) {

		Date time1 = new Date();
		
		QueryData result = new QueryData();
		
		if(!isEmpty(objm)){
			result = queryBypage(objm, query, pageNumber, locCount);
			return result;
		}

		if (query.getPageArray() == null && pageNumber != null) {
			query.setPageArray(new int[] { pageNumber });
		} else {
			query.setPageArray(new int[] { 1 });
		}
		
		ListQuery queryMap = query.format();
		
		// 查询符合条件的对象数量
		int allCount = locationQueryDAO.countObjNum(queryMap);
		result.setTotalCount(allCount);
		
		// 计算总页数
		int totalPage = QueryData.computeTotalPage(allCount, query.getRecordPerPage());
		result.setTotalPage(totalPage);

		// 查询的起始位置和长度
		int startIndex = (pageNumber-1)*query.getRecordPerPage();
		queryMap.fill("startIndex", startIndex);
		queryMap.fill("maxCount", query.getRecordPerPage());
		
		// 查询符合条件的对象
		objectList = locationQueryDAO.queryObjects(queryMap);

		log.debug("objectsize:" + objectList.size());

		// 对每个对象进行地理位置查询操作
		Iterator objIterator = objectList.iterator();
		while (objIterator.hasNext()) {
			ObjModel object = (ObjModel) objIterator.next();
			log.debug("imei:" + object.getImei() + "\nimsi:" + object.getImsi());

			// 查询该对象对应的地理位置的第一页
			CommonQuery locQuery = new CommonQuery();
			locQuery.setRecordPerPage(locCount);
			int locPageNum = 1;
			List<LocationSimple> locList = new ArrayList<LocationSimple>();
			locList = this.queryLocationByPage(locQuery, 1, object);
			if(locList.size()>0){
				String time = FormatUtil.formatDate(locList.get(0).getTime()) + " - "
						+ FormatUtil.formatDate(locList.get(locList.size()-1).getTime());
				object.setTime(time);
			}
			object.setItems(locList);
		}

		// 为每个对象编号
		int objId = 0;
		Iterator objIter = objectList.iterator();
		while (objIter.hasNext()) {
			ObjModel object = (ObjModel) objIter.next();
			object.setObjId(++objId);
		}
		log.debug("objectSize:" + objectList.size());
		Date time2 = new Date();
		long time = time2.getTime() - time1.getTime();
		log.debug("service运行时间：" + time);
		List<PageData> pageDataList = Lists.newArrayList();
		return PageHandle.initialQueryData(result, pageDataList, query,
				objectList, totalPage);
	}

	/**
	 * @description 通过已有的对象ID查询对应的地理位置信息
	 * @param query
	 * @param locPageNum
	 * @param objId
	 * @return QueryData
	 */
	public QueryData queryLocationByPage(CommonQuery query, Integer locPageNum,
			Integer objId) {
		QueryData result = new QueryData();
		nowObject = new ObjModel();
		for (int i = 0; i < objectList.size(); i++) {
			ObjModel obj = objectList.get(i);
			int id = obj.getObjId();
			if (objId == id) {
				nowObject = objectList.get(i);
			}
		}
		log.debug("objectID:" + nowObject.getObjId());

		if (nowObject.getObjId() == null) {
			log.debug("object is null");
			return null;
		}
		result = this.queryLocByPage(query, locPageNum, nowObject);
		return result;

	}

	/**
	 * @description 通过对象分页查询其位置信息
	 * @param query
	 * @param locPageNum
	 * @param object
	 * @return QueryData
	 */
	public QueryData queryLocByPage(CommonQuery query, Integer locPageNum,
			ObjModel object) {
		QueryData result = new QueryData();
		int totalPage = 0;
		String imei = object.getImei();
		String imsi = object.getImsi();

		ListQuery queryMap = query.format();
		queryMap.fill("imei", imei);
		queryMap.fill("imsi", imsi);

		if (object.getLongitudeA() != null
				&& !object.getLongitudeA().equals("")
				&& object.getLongitudeB() != null
				&& !object.getLongitudeB().equals("")
				&& object.getLatitudeA() != null
				&& !object.getLatitudeA().equals("")
				&& object.getLatitudeB() != null
				&& !object.getLatitudeB().equals("")) {
			double longitudeA = Double.parseDouble(object.getLongitudeA());
			double longitudeB = Double.parseDouble(object.getLongitudeB());
			double latitudeA = Double.parseDouble(object.getLatitudeA());
			double latitudeB = Double.parseDouble(object.getLatitudeB());
			queryMap.fill("longitudeA", longitudeA);
			queryMap.fill("longitudeB", longitudeB);
			queryMap.fill("latitudeA", latitudeA);
			queryMap.fill("latitudeB", latitudeB);
			log.debug(longitudeA + " " + longitudeB + " " + latitudeA
					+ " " + latitudeB);
		}

		int allCount = locationQueryDAO.countLocationNum(queryMap);
		log.debug("locationCount:" + allCount);

		List<LocationSimple> locList = new ArrayList<LocationSimple>();
		locList = this.queryLocationByPage(query, locPageNum, object);
		

		// 设置总数量
		result.setTotalCount(allCount);
		// 计算总页数
		totalPage = QueryData.computeTotalPage(allCount,
				query.getRecordPerPage());
		result.setTotalPage(totalPage);
		List<PageData> pageDataList = Lists.newArrayList();
		return PageHandle.initialQueryData(result, pageDataList, query,
				locList, totalPage);
	}

	/**
	 * @description 分页查询位置时，查询某一页的信息
	 * @param query
	 * @param locPageNum
	 * @param object
	 * @return List<LocationSimple>
	 */
	public List<LocationSimple> queryLocationByPage(CommonQuery query,
			Integer locPageNum, ObjModel object) {

		String imei = object.getImei();
		String imsi = object.getImsi();
		
		log.debug("IMEI:"+object.getImei());

		ListQuery queryMap = query.format();
		queryMap.fill("imei", imei);
		queryMap.fill("imsi", imsi);

		if (object.getLongitudeA() != null && object.getLongitudeB() != null
				&& object.getLatitudeA() != null
				&& object.getLatitudeB() != null) {
			double longitudeA = Double.parseDouble(object.getLongitudeA());
			double longitudeB = Double.parseDouble(object.getLongitudeB());
			double latitudeA = Double.parseDouble(object.getLatitudeA());
			double latitudeB = Double.parseDouble(object.getLatitudeB());
			queryMap.fill("longitudeA", longitudeA);
			queryMap.fill("longitudeB", longitudeB);
			queryMap.fill("latitudeA", latitudeA);
			queryMap.fill("latitudeB", latitudeB);
		}

		if (query.getRecordPerPage() <= 0) {
			query.setRecordPerPage(20);
		}
		if (query.getPageArray() == null && locPageNum != null) {
			query.setPageArray(new int[] { locPageNum });
		} else {
			query.setPageArray(new int[] { 1 });
		}

		// 查询的起始位置和长度
		int startIndex = (locPageNum - 1) * query.getRecordPerPage();
		int endIndex = query.getRecordPerPage();
		queryMap.fill("startIndex", startIndex);
		queryMap.fill("endIndex", endIndex);
		// 时间限制
		Date dateStart = object.getDateStart();
		Date dateEnd = object.getDateEnd();
	
		log.debug("dateStart:"+dateStart+"\ndateEnd:"+dateEnd);
		queryMap.fill("dateStart", dateStart);
		queryMap.fill("dateEnd", dateEnd);

		List<LocationSimple> locList = new ArrayList<LocationSimple>();
		locList = locationQueryDAO.queryLocByPage(queryMap);
		int dataId = startIndex;
		for (int i = 0; i < locList.size(); i++) {
			LocationSimple location = locList.get(i);
			location.setDataId(++dataId);
		}
		return locList;
	}

	/**
	 * @description 导出location到excel文件
	 * @param list
	 * @return
	 */
	public HSSFWorkbook export(List<LocationSimple> list) {
		String[] excelHeader = { "编号", "经度", "纬度", "日期", "手机型号", "APP类型",
				"源IP", "目的IP" };
		String sheetName = "Location";
		if (nowObject != null) {
			sheetName = nowObject.getName();
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		// 设置居中样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

		HSSFCell cell = null;
		for (int i = 0; i < excelHeader.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}

		for (int j = 0; j < list.size(); j++) {

			row = sheet.createRow((int) j + 1);
			LocationSimple location = list.get(j);

			cell = row.createCell(0);
			cell.setCellValue(location.getDataId());

			cell = row.createCell(1);
			cell.setCellValue(location.getLongitude());

			cell = row.createCell(2);
			cell.setCellValue(location.getLatitude());

			cell = row.createCell(3);
			cell.setCellValue(location.getTimeStr());

			cell = row.createCell(4);
			cell.setCellValue(location.getPhoneType());

			cell = row.createCell(5);
			cell.setCellValue(location.getAppType());

			cell = row.createCell(6);
			cell.setCellValue(location.getSrcIP());

			cell = row.createCell(7);
			cell.setCellValue(location.getDstIP());

		}
		return wb;
	}

	/**
	 * @author chenjiao
	 * @description 查询当前对象的所有位置信息
	 * @return
	 */
	public List<LocationSimple> queryLocByObject() {

		if (nowObject == null) {
			return null;
		}

		String imei = nowObject.getImei();
		String imsi = nowObject.getImsi();

		CommonQuery query = new CommonQuery();
		ListQuery queryMap = query.format();
		queryMap.put("imei", imei);
		queryMap.put("imsi", imsi);

		if (nowObject.getLongitudeA() != null
				&& nowObject.getLongitudeB() != null
				&& nowObject.getLatitudeA() != null
				&& nowObject.getLatitudeB() != null) {
			double longitudeA = Double.parseDouble(nowObject.getLongitudeA());
			double longitudeB = Double.parseDouble(nowObject.getLongitudeB());
			double latitudeA = Double.parseDouble(nowObject.getLatitudeA());
			double latitudeB = Double.parseDouble(nowObject.getLatitudeB());
			queryMap.put("longitudeA", longitudeA);
			queryMap.put("longitudeB", longitudeB);
			queryMap.put("latitudeA", latitudeA);
			queryMap.put("latitudeB", latitudeB);
		}
		List<LocationSimple> locList = locationQueryDAO
				.queryLocByObject(queryMap);
		for (int dataId = 0; dataId < locList.size(); dataId++) {
			LocationSimple loc = new LocationSimple();
			loc = locList.get(dataId);
			loc.setDataId(dataId + 1);
			Date time = new Date();
			time = loc.getTime();
			String timeStr = FormatUtil.formatDate(time);
			loc.setTimeStr(timeStr);
		}

		log.debug("location list size: " + locList.size());
		return locList;
	}
	
	
	/**
	 * @description 判断imei、imsi、phone、时间段、区域是否全为空值
	 * @param object
	 * @return
	 */
	public boolean isEmpty(ObjModel object){
		
		if(!object.getImei().equals("")){
			return false;
		}
		if(!object.getImsi().equals("")){
			return false;
		}
		if(!object.getPhone().equals("")){
			return false;
		}
		if(!object.getTime().equals("")){
			return false;
		}	
		if(!object.getLongitudeA().equals("")){
			return false;
		}
		if(!object.getLongitudeB().equals("")){
			return false;
		}
		if(!object.getLatitudeA().equals("")){
			return false;
		}
		if(!object.getLatitudeB().equals("")){
			return false;
		}
		return true;
		
	}
}
