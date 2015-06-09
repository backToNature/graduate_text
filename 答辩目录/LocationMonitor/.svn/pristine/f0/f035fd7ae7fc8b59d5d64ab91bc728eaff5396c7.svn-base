package com.swust.kelab.repos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swust.kelab.domain.Alert;
import com.swust.kelab.domain.AlertConfig;
import com.swust.kelab.model.ConfigAlertModel;
import com.swust.kelab.repos.bean.ListQuery;
import com.swust.kelab.service.engine.JobLauncherDetails;

@Repository("alertConfigDAO")
public class AlertConfigDAO {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 增加一条告警设置
	public Integer addOneAlertConfig(AlertConfig alertConfig) {
		return this.sqlSession.insert("alertConfigMapper.insertOne",
				alertConfig);
	}

	// 删除一条告警设置
	public Integer deleteOneAlertConfig(int alertConfigID) {
		return this.sqlSession.delete("alertConfigMapper.deleteOne",
				alertConfigID);

	}

	// 查询该用户告警设置的数量
	public Integer countConfigNum(ListQuery queryMap) {
		return this.sqlSession.selectOne("alertConfigMapper.countConfigNum",
				queryMap);
	}

	// 查询某用户的所有告警配置
	public List<ConfigAlertModel> queryConfigs(ListQuery queryMap) {
		return this.sqlSession.selectList("alertConfigMapper.queryConfigs",
				queryMap);
	}

	// 查看某告警设置某一页告警信息
	public List<Alert> queryAlertList(ListQuery queryMap) {
		List<Alert> alertList = new ArrayList<Alert>();
		alertList = this.sqlSession.selectList(
				"alertConfigMapper.queryAlertList", queryMap);
		log.debug("alertListSize:" + alertList.size());
		return alertList;
	}

	// 删除告警配置下的告警
	public Integer deleteAlerts(int alertConfigID) {
		return this.sqlSession.delete("alertConfigMapper.deleteAlerts",
				alertConfigID);
	}

	// 查询某条告警配置产生的告警数量
	public Integer countAlertNum(Integer alertConfigID) {
		return this.sqlSession.selectOne("alertConfigMapper.selectAlertCount",
				alertConfigID);
	}
}
