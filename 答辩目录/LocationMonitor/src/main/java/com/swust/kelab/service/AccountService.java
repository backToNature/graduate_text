package com.swust.kelab.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.swust.kelab.domain.Account;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.PageData;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.repos.AccountDAO;
import com.swust.kelab.repos.bean.ListQuery;
import com.swust.kelab.util.PageHandle;

@Service(value = "accountService")
public class AccountService {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountDAO accountDAO;
    /**
     * 分页查询意见领袖特点
     * @param query 分页信息
     * @return
     */
    public QueryData viewLeaders(CommonQuery query){
    	QueryData result = new QueryData();
		//Account修改成自己的查询对象
    	List<Account> list = null;
		int totalPage = 0;
		ListQuery queryMap = query.format();
		//需要修改成自己的
		int allCount = accountDAO.countLeadersNum();
		
		result.setTotalCount(allCount);
		if (query.getRecordPerPage() <= 0) {
			query.setRecordPerPage(20); // 每页20条
		}
		totalPage = QueryData.computeTotalPage(allCount,
				query.getRecordPerPage());
		result.setTotalPage(totalPage);
		if (query.getPageArray() == null) {
			query.setPageArray(new int[] { 1, 2, 3 });
		}
		int startIndex = (query.getPageArray()[0] - 1)
				* query.getRecordPerPage();
		int endIndex = query.getPageArray()[query.getPageArray().length - 1]
				* query.getRecordPerPage();
		queryMap.fill("startIndex", startIndex);
		queryMap.fill("maxCount", endIndex);
		
		//修改成自己的
		list = accountDAO.viewLeaders(queryMap);
		
		List<PageData> pageDataList = Lists.newArrayList();
		return PageHandle.initialQueryData(result, pageDataList, query, list,
				totalPage);
    }
}
