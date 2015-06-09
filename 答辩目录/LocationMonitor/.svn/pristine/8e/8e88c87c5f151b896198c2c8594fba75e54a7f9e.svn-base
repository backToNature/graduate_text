package com.swust.kelab.web.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swust.kelab.domain.LocationSimple;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.ObjModel;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.service.LocationQueryService;
import com.swust.kelab.service.engine.JobLauncherDetails;
import com.swust.kelab.util.FormatUtil;
import com.swust.kelab.web.json.JsonAndView;

@Controller
@RequestMapping("/locationQuery")
public class LocationQueryController {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private LocationQueryService locationQueryService;
	private static final Integer DEFAULT_LOCCOUNT = 20;
	private static final Integer DEFAULT_PAGENUMBER = 1;

	/**
	 * @description 分页查询对象和位置信息
	 * @param objm
	 * @param query
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/queryByPage", method = RequestMethod.GET)
	public JsonAndView query(ObjModel objm, CommonQuery query,
			Integer pageNumber, Integer locCount) {
		JsonAndView jv = new JsonAndView();
		try {
			log.debug("controller page:" + pageNumber + "\nrecordPerPage:"
					+ query.getRecordPerPage());
			if (locCount == null || locCount <= 0) {
				locCount = DEFAULT_LOCCOUNT;
			}
			if (pageNumber == null || pageNumber <= 0) {
				pageNumber = DEFAULT_PAGENUMBER;
			}
			query.setRecordPerPage(6);
			QueryData result = locationQueryService.queryObjBypage(objm, query,
					pageNumber, locCount);
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
	 * @description 分页查询地理位置
	 * @param locCount
	 * @param locPageNum
	 * @param objId
	 * @return List<LocationSimple>
	 */
	@RequestMapping(value = "/queryLocation", method = RequestMethod.GET)
	public JsonAndView queryLocationByPage(Integer locCount,
			Integer locPageNum, Integer objId) {
		CommonQuery query = new CommonQuery();
		if (locCount == null || locCount <= 0) {
			locCount = DEFAULT_LOCCOUNT;
		}
		query.setRecordPerPage(locCount);
		JsonAndView jv = new JsonAndView();
		if (locPageNum == null || locPageNum <= 0) {
			locPageNum = DEFAULT_PAGENUMBER;
		}
		try {
			QueryData result = locationQueryService.queryLocationByPage(query,
					locPageNum, objId);
			if (result == null) {
				jv.setRet(false);
				jv.setErrcode(1);
				jv.setErrmsg("object is not found");
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
	 * @description 将位置信息导出到excel文件中
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exToExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<LocationSimple> locationList = new ArrayList<LocationSimple>();
		locationList = locationQueryService.queryLocByObject();
		if (locationList != null) {
			HSSFWorkbook wb = locationQueryService.export(locationList);

			Date timeNow = new Date();
			String timeStr = FormatUtil.formatDate(timeNow, "yyyyMMddHHmmss");
			String fileName = "location" + timeStr + ".xls";
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
