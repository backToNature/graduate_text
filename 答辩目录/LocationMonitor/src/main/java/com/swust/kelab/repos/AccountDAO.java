package com.swust.kelab.repos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swust.kelab.domain.Account;

@Repository("accountDAO")
public class AccountDAO {
    private SqlSession sqlSession;
    
    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public List<Account> selectAllAccounts(){
        return sqlSession.selectList("account.selectAll");
    }
    
    public void resetAccounts(){
        sqlSession.update("account.reset");
    }
    
    public void setLeaderRank(Map map){
        sqlSession.update("account.setLeaderRank", map);
    }
    
    public Integer countLeadersNum(){
        return sqlSession.selectOne("account.countLeadersNum");
    }
    
    public List<Account> viewLeaders(Map query){
        return sqlSession.selectList("account.viewLeaders",query);
    }
    
    public Account viewOneLeader(int accoId){
        return sqlSession.selectOne("account.viewOneLeader", accoId);
    }
    
    public Integer countUsersNum(){
        return  sqlSession.selectOne("account.countUsersNum");
    }
}
