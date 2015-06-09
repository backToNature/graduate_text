package com.swust.kelab.model;

import java.util.ArrayList;
import java.util.List;

public class WebNode {
	private List<Integer> fromUrlIds;//节点的入边
	private List<Double> fromEdgeValue;//边的权值
	private double pageRandValue;
	private int outDegree;//出度
	private Double outEdgeValue;//出边的权值总和
	private boolean dead;//是否为死链
	private int outEffictiveDegree;//有效出度
	private Double effectOutEdgeValue;//有效的出边的权值总和
	private int accoId;
	private int index;
	private double nodeValue;	
	
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAccoId() {
        return accoId;
    }

    public void setAccoId(int accoId) {
        this.accoId = accoId;
    }

    public double getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(double nodeValue) {
        this.nodeValue = nodeValue;
    }

    public List<Double> getFromEdgeValue() {
        return fromEdgeValue;
    }

    public void setFromEdgeValue(List<Double> fromEdgeValue) {
        this.fromEdgeValue = fromEdgeValue;
    }

    public Double getOutEdgeValue() {
        return outEdgeValue;
    }

    public void setOutEdgeValue(Double outEdgeValue) {
        this.outEdgeValue = outEdgeValue;
    }

    public Double getEffectOutEdgeValue() {
        return effectOutEdgeValue;
    }

    public void setEffectOutEdgeValue(Double effectOutEdgeValue) {
        this.effectOutEdgeValue = effectOutEdgeValue;
    }

    public WebNode(){
		fromUrlIds=new ArrayList<Integer>();
		fromEdgeValue=new ArrayList<Double>();
		outDegree=0;
		outEdgeValue=0.0;
		dead=false;		
	}
	
	public int getOutEffictiveDegree() {
		return outEffictiveDegree;
	}

	public void setOutEffictiveDegree(int outEffictiveDegree) {
		this.outEffictiveDegree = outEffictiveDegree;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public List<Integer> getFromUrlIds() {
		return fromUrlIds;
	}
	public void setFromUrlIds(List<Integer> fromUrlIds) {
		this.fromUrlIds = fromUrlIds;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}	
	public double getPageRandValue() {
		return pageRandValue;
	}
	public void setPageRandValue(double pageRandValue) {
		this.pageRandValue = pageRandValue;
	}
	
}
