package com.swust.kelab.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.swust.kelab.domain.Account;
import com.swust.kelab.domain.User;
import com.swust.kelab.model.CommonQuery;
import com.swust.kelab.model.PageData;
import com.swust.kelab.model.QueryData;
import com.swust.kelab.repos.UserDAO;
import com.swust.kelab.repos.bean.ListQuery;
import com.swust.kelab.service.engine.JobLauncherDetails;
import com.swust.kelab.util.PageHandle;

@Service("userService")
public class UserService {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Autowired
	private UserDAO userDAO;

	public int addOneUser(User u) throws Exception {
		return userDAO.addOneUser(u);
	}

	public List<User> selectUser(String num, String name) throws Exception {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("name", name);
		query.put("num", num);
		return userDAO.queryList(query);
	}

	public QueryData queryByPage(CommonQuery query, String name)
			throws Exception {
		QueryData result = new QueryData();
		// Account修改成自己的查询对象
		List<User> list = null;
		int totalPage = 0;
		ListQuery queryMap = query.format();
		// 设置新的查询参数
		queryMap.fill("name", name);
		// 需要修改成自己的
		int allCount = userDAO.countUsersNum(queryMap);

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
		log.debug(startIndex + ":" + endIndex);
		queryMap.fill("startIndex", startIndex);
		queryMap.fill("maxCount", endIndex);

		// 修改成自己的
		list = userDAO.viewLeaders(queryMap);

		List<PageData> pageDataList = Lists.newArrayList();
		return PageHandle.initialQueryData(result, pageDataList, query, list,
				totalPage);
	}

	public int deleteOneUser(int id) throws Exception {
		return userDAO.deleteOneUser(id);
	}

	public int updateOne(User user) throws Exception {
		return userDAO.updateOne(user);
	}
}
