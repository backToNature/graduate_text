package com.swust.kelab.domain;

import java.util.List;

/**
 * 微博用户
 * @author WuXianhui
 *
 */
public class Account {    
    private int accoId;
    private String accoUid;
    private String nickname;
    private int leaderRank;
    private int weiboNum;
    private int commentNum;
    private int repostNum;
    private int totalWeiboNum;
    private int attationNum;
    private int fansNum;
    private int mutualNum;
    private int verify;
    private String verifyStr;
  //  private List<Weibo> weibos;
    
    //下面为以上各项归一化（离差标准化）后的值，以及最终的节点权重
    private double relateNumValue;//主题相关的发帖和评论数的值
    private double totalWeiboValue;
    private double attationValue;
    private double fansValue;
    private double mutualValue;
    private double finalValue;
    
/*    public List<Weibo> getWeibos() {
        return weibos;
    }
    public void setWeibos(List<Weibo> weibos) {
        this.weibos = weibos;
    }*/
    public double getRelateNumValue() {
        return relateNumValue;
    }
    public void setRelateNumValue(double relateNumValue) {
        this.relateNumValue = relateNumValue;
    }
    public double getTotalWeiboValue() {
        return totalWeiboValue;
    }
    public void setTotalWeiboValue(double totalWeiboValue) {
        this.totalWeiboValue = totalWeiboValue;
    }
    public double getAttationValue() {
        return attationValue;
    }
    public void setAttationValue(double attationValue) {
        this.attationValue = attationValue;
    }
    public double getFansValue() {
        return fansValue;
    }
    public void setFansValue(double fansValue) {
        this.fansValue = fansValue;
    }
    public double getMutualValue() {
        return mutualValue;
    }
    public void setMutualValue(double mutualValue) {
        this.mutualValue = mutualValue;
    }
    public double getFinalValue() {
        return finalValue;
    }
    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }
    public int getAccoId() {
        return accoId;
    }
    public void setAccoId(int accoId) {
        this.accoId = accoId;
    }
    public String getAccoUid() {
        return accoUid;
    }
    public void setAccoUid(String accoUid) {
        this.accoUid = accoUid;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getLeaderRank() {
        return leaderRank;
    }
    public void setLeaderRank(int leaderRank) {
        this.leaderRank = leaderRank;
    }
    public int getWeiboNum() {
        return weiboNum;
    }
    public void setWeiboNum(int weiboNum) {
        this.weiboNum = weiboNum;
    }
    public int getCommentNum() {
        return commentNum;
    }
    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
    public int getRepostNum() {
        return repostNum;
    }
    public void setRepostNum(int repostNum) {
        this.repostNum = repostNum;
    }
    public int getTotalWeiboNum() {
        return totalWeiboNum;
    }
    public void setTotalWeiboNum(int totalWeiboNum) {
        this.totalWeiboNum = totalWeiboNum;
    }
    public int getAttationNum() {
        return attationNum;
    }
    public void setAttationNum(int attationNum) {
        this.attationNum = attationNum;
    }
    public int getFansNum() {
        return fansNum;
    }
    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }
    public int getMutualNum() {
        return mutualNum;
    }
    public void setMutualNum(int mutualNum) {
        this.mutualNum = mutualNum;
    }
    public int getVerify() {
        return verify;
    }
    public void setVerify(int verify) {
        this.verify = verify;
    }
    public String getVerifyStr() {
        return verifyStr;
    }
    public void setVerifyStr(String verifyStr) {
        this.verifyStr = verifyStr;
    }
    
    
}
