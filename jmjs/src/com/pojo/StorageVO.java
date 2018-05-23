package com.pojo;

import java.sql.Date;

public class StorageVO{

	private Integer sId;
	private String sGoodsName;
	private String sSupplierName;
	//平均单价
	private Double sAvgPrice;
	private String sType;
	//总库存量
	private Integer sSumStockNum;
	//总价格
	private Double sSumPrice;
	
	
	/**
	 * @return the sSumStockNum
	 */
	public Integer getsSumStockNum() {
		return sSumStockNum;
	}
	/**
	 * @param sSumStockNum the sSumStockNum to set
	 */
	public void setsSumStockNum(Integer sSumStockNum) {
		this.sSumStockNum = sSumStockNum;
	}
	/**
	 * @return the sSumPrice
	 */
	public Double getsSumPrice() {
		return sSumPrice;
	}
	/**
	 * @param sSumPrice the sSumPrice to set
	 */
	public void setsSumPrice(Double sSumPrice) {
		this.sSumPrice = sSumPrice;
	}
	/**
	 * @return the sId
	 */
	public Integer getsId() {
		return sId;
	}
	/**
	 * @param sId the sId to set
	 */
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	/**
	 * @return the sGoodsName
	 */
	public String getsGoodsName() {
		return sGoodsName;
	}
	/**
	 * @param sGoodsName the sGoodsName to set
	 */
	public void setsGoodsName(String sGoodsName) {
		this.sGoodsName = sGoodsName;
	}
	/**
	 * @return the sSupplierName
	 */
	public String getsSupplierName() {
		return sSupplierName;
	}
	/**
	 * @param sSupplierName the sSupplierName to set
	 */
	public void setsSupplierName(String sSupplierName) {
		this.sSupplierName = sSupplierName;
	}
	/**
	 * @return the sAvgPrice
	 */
	public Double getsAvgPrice() {
		return sAvgPrice;
	}
	/**
	 * @param sAvgPrice the sAvgPrice to set
	 */
	public void setsAvgPrice(Double sAvgPrice) {
		this.sAvgPrice = sAvgPrice;
	}
	/**
	 * @return the sType
	 */
	public String getsType() {
		return sType;
	}
	/**
	 * @param sType the sType to set
	 */
	public void setsType(String sType) {
		this.sType = sType;
	}
	
}









