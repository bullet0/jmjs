package com.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Depot{

	private String dId;
	private Integer dVarietyNum;
	private Integer dTotalPrice;
	private String dDate;
	private String dSettlementWay;
	private List<Purchase>  purchases = new ArrayList<>();
	
	
	
	
	/**
	 * @return the purchases
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * @param purchases the purchases to set
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	/**
	 * @return the dId
	 */
	public String getdId() {
		return dId;
	}

	/**
	 * @param dId the dId to set
	 */
	public void setdId(String dId) {
		this.dId = dId;
	}

	public Integer getdVarietyNum() {
		return dVarietyNum;
	}

	public void setdVarietyNum(Integer dVarietyNum) {
		this.dVarietyNum = dVarietyNum;
	}
	
	
	
	
	
	
	
	public Integer getdTotalPrice() {
		return dTotalPrice;
	}

	public void setdTotalPrice(Integer dTotalPrice) {
		this.dTotalPrice = dTotalPrice;
	}
	
	
	
	
	
	
	
	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	
	
	
	
	
	
	
	public String getdSettlementWay() {
		return dSettlementWay;
	}

	public void setdSettlementWay(String dSettlementWay) {
		this.dSettlementWay = dSettlementWay;
	}

	
	
	
	

}









