package com.swust.kelab.repos;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swust.kelab.domain.Location;

@Repository("locationDAO")
public class LocationDAO {
	 private SqlSession sqlSession;
	    @Autowired
	    public void setSqlSession(SqlSession sqlSession) {
	        this.sqlSession = sqlSession;
	    }

	public int add(Location location) {
		return this.sqlSession.insert("location.insertOne",location);
		
	}

	public int delete(Location location) {
		return this.sqlSession.delete("location.deleteOne",location);
	}

	public int update(Location location) {
		return this.sqlSession.update("location.updataOne",location);
	}

	public Location selectIMEI(String imei) {
		return this.sqlSession.selectOne("location.selectIMEI", imei);
	}
	public Location selectIMSI(String imsi) {
		return this.sqlSession.selectOne("location.selectIMSI", imsi);
	}



}
