package com.swust.kelab.web.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.domain.Alert;
import com.swust.kelab.domain.AlertConfig;
import com.swust.kelab.domain.LocationSimple;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.service.AlertConfigService;
import com.swust.kelab.service.engine.JobLauncherDetails;
import com.swust.kelab.util.FormatUtil;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/alertConfig")
public class AlertConfigController {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private AlertConfigService alertConfigService;
	private static final Integer userID = 1;
	private static final Integer RECORDPERPAGE = 20;
	private static final Integer ALERTPAGECOUNT = 20;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd/ HH:mm:ss");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * 增加一个告警设置
	 * 
	 * @param alertConfig
	 * @return 是否添加成功
	 */
	@RequestMapping(value = "/addOne", method = RequestMethod.GET)
	public JsonAndView addOne(AlertConfig alertConfig) {
		JsonAndView jv = new JsonAndView();
		alertConfig.setUserID(userID);
		if (alertConfig.getConfigTime() == null) {
			alertConfig.setConfigTime(new Date());
		}
		alertConfigService.addOneAlertConfig(alertConfig);
		jv.setRet(true);
		return jv;
	}

	/**
	 * 删除一个告警设置
	 * 
	 * @param alertConfigID
	 * @return 是否删除成功
	 */
	@RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
	public JsonAndView deleteOneAlertConfig(int alertConfigID) {
		JsonAndView jv = new JsonAndView();
		try {
			int flag = alertConfigService.deleteOneAlertConfig(alertConfigID);
		} catch (Exception e) {
			jv.setRet(false);
			jv.setErrmsg("delete failed");
		}
		jv.setRet(true);
		return jv;
	}

	/**
	 * @description 分页查询告警配置和对应的告警
	 * @param query
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/queryByPage", method = RequestMethod.GET)
	public JsonAndView query(Integer recordPerPage, Integer pageNumber) {
		JsonAndView jv = new JsonAndView();
		if (recordPerPage == null || recordPerPage <= 0) {
			recordPerPage = RECORDPERPAGE;
		}
		if (pageNumber == null || pageNumber <= 0) {
			pageNumber = 1;
		}
		CommonQuery query = new CommonQuery();
		query.setRecordPerPage(recordPerPage);
		Integer alertPageCount = ALERTPAGECOUNT;

		log.debug("recordPerPage:" + query.getRecordPerPage() + "\npageNumber:"
				+ pageNumber);
		try {
			QueryData result = alertConfigService.queryBypage(query,
					pageNumber, userID, alertPageCount);
			if (result == null) {
				jv.setRet(false);
				jv.setErrcode(1);
				jv.setErrmsg("null");
			} else {
				jv.addData("totalPage", result.getTotalPage());
				jv.addData("totalCount", result.getTotalCount());
				jv.addData("items", result.getPageData());
			}
		} catch (Exception e) {

		}
		return jv;
	}

	/**
	 * 
	 * @param alertConfigID
	 * @param alertPageCount
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/queryAlertByPage", method = RequestMethod.GET)
	public JsonAndView queryAlertByPage(Integer alertConfigID,
			Integer alertPageCount, Integer pageNumber) {
		log.debug("----------queryAlert-------");
		JsonAndView jv = new JsonAndView();
		if (alertPageCount == null || alertPageCount <= 0) {
			alertPageCount = ALERTPAGECOUNT;
		}
		if (pageNumber == null || pageNumber <= 0) {
			pageNumber = 1;
		}
		CommonQuery query = new CommonQuery();
		query.setRecordPerPage(alertPageCount);

		try {
			log.debug("alertConigID:" + alertConfigID);
			QueryData result = alertConfigService.queryAlertBypage(
					alertConfigID, query, pageNumber);
			if (result == null) {
				jv.setRet(false);
				jv.setErrcode(1);
				jv.setErrmsg("null");
			} else {
				jv.addData("totalPage", result.getTotalPage());
				jv.addData("totalCount", result.getTotalCount());
				jv.addData("items", result.getPageData());
			}
		} catch (Exception e) {

		}
		return jv;
	}

	/**
	 * @description 导出告警数据到EXCEL文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exToExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Alert> alertList = new ArrayList<Alert>();
		alertList = alertConfigService.queryAllAlert();
		if (alertList != null) {
			HSSFWorkbook wb = alertConfigService.export(alertList);

			Date timeNow = new Date();
			String timeStr = FormatUtil.formatDate(timeNow, "yyyyMMddHHmmss");
			String fileName = "alert" + timeStr + ".xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		}
	}

}
