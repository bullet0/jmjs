package com.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Sale{

	private Integer sId;
	private String sNo;
	private Integer sVarietyNum;
	private Integer sTotalPrice;
	private String sSaleDate;
	private String sSettlementWay;
	private Customer customerId;
	private String customerName;
	private List<SaleDetail> saleDetails = new ArrayList<SaleDetail>();
	
	
	/**
	 * @return the saleDetails
	 */
	public List<SaleDetail> getSaleDetails() {
		return saleDetails;
	}

	/**
	 * @param saleDetails the saleDetails to set
	 */
	public void setSaleDetails(List<SaleDetail> saleDetails) {
		this.saleDetails = saleDetails;
	}

	/**
	 * @return the sNo
	 */
	public String getsNo() {
		return sNo;
	}

	/**
	 * @param sNo the sNo to set
	 */
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}
	
	
	
	
	
	
	
	public Integer getsVarietyNum() {
		return sVarietyNum;
	}

	public void setsVarietyNum(Integer sVarietyNum) {
		this.sVarietyNum = sVarietyNum;
	}
	
	
	
	
	
	
	
	public Integer getsTotalPrice() {
		return sTotalPrice;
	}

	public void setsTotalPrice(Integer sTotalPrice) {
		this.sTotalPrice = sTotalPrice;
	}
	
	
	
	
	
	
	
	public String getsSaleDate() {
		return sSaleDate;
	}

	public void setsSaleDate(String sSaleDate) {
		this.sSaleDate = sSaleDate;
	}
	
	
	
	
	
	
	
	public String getsSettlementWay() {
		return sSettlementWay;
	}

	public void setsSettlementWay(String sSettlementWay) {
		this.sSettlementWay = sSettlementWay;
	}
	
	
	/**
	 * @return the customerId
	 */
	public Customer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	
	

}









