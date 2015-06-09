package com.swust.kelab.repos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.swust.kelab.domain.LocationSimple;
import com.swust.kelab.model.ObjModel;
import com.swust.kelab.repos.bean.ListQuery;

@Repository("locationQueryDAO")
public class LocationQueryDAO {
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 通过对象查询地理位置信息
	 * 
	 * @param obj
	 * @return List<LocationSimple>
	 */
	public List<LocationSimple> queryLocation(ObjModel obj) {
		return this.sqlSession.selectList("locationQuery.selectLocation", obj);
	}

	public Integer countObjNum(ListQuery queryMap) {
		return this.sqlSession.selectOne("locationQuery.selectCount", queryMap);
	}

	public List<ObjModel> queryObjects(ListQuery queryMap) {
		return this.sqlSession.selectList("locationQuery.selectObjByQueryMap",
				queryMap);
	}

	public Integer countLocationNum(ListQuery queryMap) {
		return this.sqlSession.selectOne("locationQuery.selectLocNumber",
				queryMap);
	}

	public List<LocationSimple> queryLocByPage(ListQuery queryMap) {
		return this.sqlSession.selectList("locationQuery.selectLocByPage",
				queryMap);
	}
	
	public List<LocationSimple> queryLocByObject(ListQuery queryMap) {
		return this.sqlSession.selectList("locationQuery.selectLocByObj", queryMap);
	}

}
