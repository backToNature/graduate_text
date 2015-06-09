package com.swust.kelab.util;

import java.util.List;

import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.PageData;
import com.swust.kelab.model.QueryData;

public class PageHandle {
	/**
	 * 查询结果分页封装
	 * 
	 * @param result
	 *            返回处理结果
	 * @param pageDataList
	 *            每一页信息
	 * @param query
	 *            查询条件 如每页大小，第几页
	 * @param list
	 *            查询结果记录
	 * @param totalPage
	 *            总页数
	 * @return
	 * 
	 * @fixbug easonlian
	 */
	public static QueryData initialQueryData(QueryData result,
			List<PageData> pageDataList, CommonQuery query,
			List<? extends Object> list, int totalPage) {
		int minPageNum = query.getPageArray()[0];
		for(int pageNum : query.getPageArray()) {
			if(pageNum<minPageNum)
				minPageNum = pageNum;
		}
		int beginPosition = QueryData.computeStartIndex(minPageNum,
						query.getRecordPerPage()); 
		for (int i = 0; i < query.getPageArray().length; i++) {
			int page = query.getPageArray()[i];
			if (page <= 0 || page > totalPage) {
				continue;
			}
			int startIndex = QueryData.computeStartIndex(page,
					query.getRecordPerPage())-beginPosition;
			int endIndex = startIndex + query.getRecordPerPage();
			if (startIndex >= list.size()) {
				break;
			}
			if (endIndex > list.size()) {
				endIndex = list.size();
			}
			List<? extends Object> pageDatas = list.subList(startIndex, endIndex);
			pageDataList.add(new PageData(page, pageDatas));
		}
		result.setPageData(pageDataList);
		return result;
	}
}
