package com.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Depot{

	private String dId;
	private String dNo;
	private Integer dVarietyNum;
	private Integer dTotalPrice;
	private String dDate;
	private String dSettlementWay;
	private List<Purchase>  purchases = new ArrayList<>();
	private Supplier supplierId;
	private String supplierName;
	
	
	
	
	
	
	/**
	 * @return the dNo
	 */
	public String getdNo() {
		return dNo;
	}

	/**
	 * @param dNo the dNo to set
	 */
	public void setdNo(String dNo) {
		this.dNo = dNo;
	}

	/**
	 * @return the supplierId
	 */
	public Supplier getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Supplier supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

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









