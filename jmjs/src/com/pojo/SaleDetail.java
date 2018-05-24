package com.pojo;

import java.sql.Date;

public class SaleDetail{

	private Integer sdId;
	private Sale saleId;
	private Goods goodsId;
	private Integer salePrice;
	private Integer saleNumber;
	private Double purchasePrice;

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}
	
	
	/**
	 * @return the saleId
	 */
	public Sale getSaleId() {
		return saleId;
	}

	/**
	 * @param saleId the saleId to set
	 */
	public void setSaleId(Sale saleId) {
		this.saleId = saleId;
	}

	/**
	 * @return the goodsId
	 */
	public Goods getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Goods goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	
	
	
	
	
	
	
	
	public Integer getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}

	/**
	 * @return the purchasePrice
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
}









